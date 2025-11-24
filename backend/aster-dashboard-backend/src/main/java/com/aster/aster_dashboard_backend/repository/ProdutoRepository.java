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
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY p.nome
    """)
    public List<TotalVendasProdutoDto> findTotalVendasProduto();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisProdutoDto(p.nome, DATE_TRUNC('month', l.dataRegistro), COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Produto p ON l.produto.id = p.id
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<VendasMensaisProdutoDto> findVendasMensaisProduto();

    @Query(value = """
        SELECT\s
            pr.nome AS produto,
            SUM(
                CASE\s
                    WHEN l.tipo = 'Mensal' THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual
                            ELSE p.preco_organizacional
                        END
                    WHEN l.tipo = 'Anual' THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual * 12
                            ELSE p.preco_organizacional * 12
                        END
                    WHEN l.tipo = 'Vitalícia' AND l.data_registro <= date_trunc('month', CURRENT_DATE) THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual
                            ELSE p.preco_organizacional
                        END
                    ELSE 0
                END
            ) AS receita
        FROM PRODUTO pr
        JOIN CONTEM c ON c.produto_id = pr.id
        JOIN PACOTE p ON p.nome = c.pacote_nome
        JOIN ADQUIRE a ON a.pacote_nome = p.nome
        JOIN LICENCA l ON l.id = a.licenca_id
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = a.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = a.cliente_documento
        WHERE pr.status = 'Comercializável'
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
                a.cliente_documento,
                c.produto_id,
                p.preco_individual,
                p.preco_organizacional,
                date_trunc('month', CURRENT_DATE) AS hoje,
        
                generate_series(
                    date_trunc('month', l.data_registro),
                    CASE\s
                        WHEN l.tipo = 'Mensal' THEN date_trunc('month', l.data_registro)
                        WHEN l.tipo = 'Anual' THEN date_trunc('month', l.data_registro) + interval '11 month'
                        WHEN l.tipo = 'Vitalícia' THEN date_trunc('month', CURRENT_DATE)
                        ELSE NULL
                    END,
                    interval '1 month'
                ) AS mes
            FROM LICENCA l
            JOIN ADQUIRE a ON a.licenca_id = l.id
            JOIN CONTEM c ON c.pacote_nome = a.pacote_nome
            JOIN PACOTE p ON p.nome = a.pacote_nome
        )
        
        SELECT
            pr.nome AS produto,
            m.mes::date AS data,   -- retorno como DATE
        
            SUM(
                CASE\s
                    WHEN m.tipo = 'Mensal' THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                            ELSE m.preco_organizacional
                        END
        
                    WHEN m.tipo = 'Anual' THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                            ELSE m.preco_organizacional
                        END
        
                    WHEN m.tipo = 'Vitalícia' THEN\s
                        CASE\s
                            WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual / 2
                            ELSE m.preco_organizacional / 2
                        END
        
                    ELSE 0
                END
            ) AS vendas
        
        FROM meses_licenca m
        JOIN PRODUTO pr ON pr.id = m.produto_id
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = m.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = m.cliente_documento
        WHERE pr.status = 'Comercializável'
        GROUP BY pr.nome, m.mes
        ORDER BY pr.nome, m.mes
    """, nativeQuery = true)
    public List<Object[]> findReceitaMensalProduto();
}
