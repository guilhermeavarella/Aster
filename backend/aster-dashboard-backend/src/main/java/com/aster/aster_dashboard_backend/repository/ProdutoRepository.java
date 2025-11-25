package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

    long countByStatus(String status);

    List<Produto> findByStatus(String status);

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ProdutoInicioDto(p.icone, CAST(AVG(f.avaliacao) AS bigdecimal), (
                SELECT pv2.id.numeroVersao
                FROM ProdutoVersao pv2
                WHERE pv2.dataLancamento = (
                        SELECT MAX(pv1.dataLancamento)
                        FROM ProdutoVersao pv1
                )
                )
        )
        FROM Produto p
        LEFT JOIN ProdutoVersao pv3 ON pv3.produto = p
        LEFT JOIN Feedback f ON f.produto = p
        GROUP BY p.id
        """)
    public List<ProdutoInicioDto> findProdutosInicio();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.TotalVendasProdutoDto(p.nome, COUNT(*))
        FROM Adquire a
        JOIN Licenca l ON a.id.licencaId = l.id
        JOIN Contem c ON c.id.pacoteNome = a.id.pacoteNome
        JOIN Produto p ON c.id.produtoId = p.id
        WHERE l.ativa = true AND l.tipo <> 'Demo' AND p.status = 'Comercializável'
        GROUP BY p.nome
        ORDER BY p.nome
    """)
    public List<TotalVendasProdutoDto> findTotalVendasProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto(p.nome, DATE_TRUNC('month', l.dataRegistro), COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        WHERE p.status = 'Comercializável' AND l.tipo <> 'Demo'
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<VendasMensaisProdutoDto> findVendasMensaisProduto();

    @Query(value = """
        SELECT
            pr.nome AS produto,
            ROUND(
                SUM(
                    CASE
                        WHEN l.tipo = 'Mensal' THEN
                            CASE
                                WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual
                                ELSE p.preco_organizacional
                            END
        
                        WHEN l.tipo = 'Anual' THEN
                            CASE
                                WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual * 12
                                ELSE p.preco_organizacional * 12
                            END
        
                        WHEN l.tipo = 'Vitalícia' THEN
                            (
                                CASE
                                    WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual / 2
                                    ELSE p.preco_organizacional / 2
                                END
                            )
                            *
                            (
                                DATE_PART(
                                    'year',
                                    age(
                                        date_trunc('month', CURRENT_DATE),
                                        date_trunc('month', l.data_registro)
                                    )
                                ) * 12
                                +
                                DATE_PART(
                                    'month',
                                    age(
                                        date_trunc('month', CURRENT_DATE),
                                        date_trunc('month', l.data_registro)
                                    )
                                )
                                + 1
                            )
        
                        ELSE 0
                    END
                )::numeric
            , 2) AS receita
        FROM PRODUTO pr
        JOIN CONTEM c ON c.produto_id = pr.id
        JOIN PACOTE p ON p.nome = c.pacote_nome
        JOIN ADQUIRE a ON a.pacote_nome = p.nome
        JOIN LICENCA l ON l.id = a.licenca_id
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = a.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = a.cliente_documento
        WHERE
            pr.status = 'Comercializável'
            AND l.ativa = TRUE
            AND p.nome IN (
                SELECT pacote_nome
                FROM CONTEM
                GROUP BY pacote_nome
                HAVING COUNT(produto_id) = 1
            )
        GROUP BY pr.nome
        ORDER BY pr.nome
    """, nativeQuery = true)
    public List<Object[]> findReceitaTotalProduto();

    @Query(value = """
        WITH meses_licenca AS (
            SELECT
                l.id AS licenca_id,
                l.tipo,
                l.data_registro,
                l.ativa,
                a.cliente_documento,
                c.produto_id,
                p.preco_individual,
                p.preco_organizacional,
                date_trunc('month', CURRENT_DATE) AS hoje,
        
                generate_series(
                    date_trunc('month', l.data_registro),
                    LEAST(
                        CASE
                            WHEN l.tipo = 'Mensal' THEN date_trunc('month', l.data_registro)
                            WHEN l.tipo = 'Anual' THEN date_trunc('month', l.data_registro) + interval '11 month'
                            WHEN l.tipo = 'Vitalícia' THEN date_trunc('month', CURRENT_DATE)
                            ELSE NULL
                        END,
                        date_trunc('month', CURRENT_DATE)
                    ),
                    interval '1 month'
                ) AS mes
            FROM LICENCA l
            JOIN ADQUIRE a ON a.licenca_id = l.id
            JOIN CONTEM c ON c.pacote_nome = a.pacote_nome
            JOIN PACOTE p ON p.nome = a.pacote_nome
        )
        
        SELECT
            pr.nome AS produto,
            m.mes::date AS data,
            ROUND(
                SUM(
                    CASE
                        WHEN m.tipo = 'Mensal' THEN
                            CASE
                                WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                                ELSE m.preco_organizacional
                            END
        
                        WHEN m.tipo = 'Anual' THEN
                            CASE
                                WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                                ELSE m.preco_organizacional
                            END
        
                        WHEN m.tipo = 'Vitalícia' THEN
                            CASE
                                WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual / 2
                                ELSE m.preco_organizacional / 2
                            END
        
                        ELSE 0
                    END
                ), 2
            ) AS vendas
        
        FROM meses_licenca m
        JOIN PRODUTO pr ON pr.id = m.produto_id
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = m.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = m.cliente_documento
        WHERE pr.status = 'Comercializável' AND m.ativa = TRUE
        GROUP BY pr.nome, m.mes
        ORDER BY pr.nome, m.mes
    """, nativeQuery = true)
    public List<Object[]> findReceitaMensalProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.entity.Produto(p.id, p.status, p.icone, p.descricaoBreve, p.descricaoCompleta, p.nome)
        FROM Adquire a
        JOIN Contem c ON c.id.pacoteNome = a.id.pacoteNome
        JOIN Produto p ON p.id = c.id.produtoId
        WHERE p.status = 'Comercializável'
        GROUP BY p.id
        ORDER BY COUNT(*) DESC, p.id
        LIMIT 4
    """)
    public List<Produto> findProdutosMaisPopulares();
}
