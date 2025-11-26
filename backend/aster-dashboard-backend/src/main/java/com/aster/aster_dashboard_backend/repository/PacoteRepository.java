package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, String> {

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.TotalVendasPacoteDto(p.nome, COUNT(*))
        FROM Adquire a
        LEFT JOIN Pacote p ON a.id.pacoteNome = p.nome
        GROUP BY p.nome
    """)
    public List<TotalVendasPacoteDto> findTotalVendasPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisPacoteDto(p.nome, CAST(DATE_TRUNC('month', l.dataRegistro) AS DATE), COUNT(*))
        FROM Adquire a
        LEFT JOIN Licenca l ON a.id.licencaId = l.id
        LEFT JOIN Pacote p ON a.id.pacoteNome = p.nome
        GROUP BY DATE_TRUNC('month', l.dataRegistro), p.nome
    """)
    public List<VendasMensaisPacoteDto> findVendasMensaisPacote();

    @Query(value = """
        SELECT
            p.nome AS pacote,
            ROUND(
                CAST(
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
                                    DATE_PART('year', age(date_trunc('month', CURRENT_DATE), date_trunc('month', l.data_registro))) * 12 +
                                    DATE_PART('month', age(date_trunc('month', CURRENT_DATE), date_trunc('month', l.data_registro))) + 1
                                )
        
                            ELSE 0
                        END
                    ) AS numeric
                ),
            2) AS receita
        FROM PACOTE p
        JOIN ADQUIRE a ON a.pacote_nome = p.nome
        JOIN LICENCA l ON l.id = a.licenca_id
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = a.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = a.cliente_documento
        WHERE l.ativa = TRUE
        GROUP BY p.nome
        ORDER BY p.nome
    """, nativeQuery = true)
    public List<Object[]> findReceitaTotalPacote();

    @Query(value = """
        WITH meses_licenca AS (
            SELECT
                l.id AS licenca_id,
                l.tipo,
                l.data_registro,
                l.ativa,
                a.cliente_documento,
                a.pacote_nome,
                p.preco_individual,
                p.preco_organizacional,
        
                generate_series(
                    date_trunc('month', l.data_registro),
        
                    LEAST(
                        CASE
                            WHEN l.tipo = 'Mensal' THEN date_trunc('month', l.data_registro)
                            WHEN l.tipo = 'Anual' THEN date_trunc('month', l.data_registro) + interval '11 month'
                            WHEN l.tipo = 'Vitalícia' THEN date_trunc('month', CURRENT_DATE)
                            ELSE NULL
                        END,
                        date_trunc('month', CURRENT_DATE)  -- nunca ultrapassar o presente
                    ),
        
                    interval '1 month'
                ) AS mes
        
            FROM LICENCA l
            JOIN ADQUIRE a ON a.licenca_id = l.id
            JOIN PACOTE p ON p.nome = a.pacote_nome
        )
        
        SELECT
            m.pacote_nome AS pacote,
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
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = m.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = m.cliente_documento
        WHERE m.ativa = TRUE
        GROUP BY m.pacote_nome, m.mes
        ORDER BY m.pacote_nome, m.mes
    """, nativeQuery = true)
    public List<Object[]> findReceitaMensalPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.MediaAvaliacoesPacoteDto(
                    p.nome,
                    CAST(AVG(f.avaliacao) AS bigdecimal)
               )
        FROM Pacote p
        JOIN Contem c ON p.nome = c.id.pacoteNome
        JOIN Produto pr ON pr.id = c.id.produtoId
        JOIN Devolutiva d ON d.produto.id = pr.id
        JOIN Feedback f ON f.id = d.id
        WHERE pr.status = 'Comercializável'
        GROUP BY p.nome
        HAVING COUNT(DISTINCT pr.id) = (
            SELECT COUNT(DISTINCT c2.id.produtoId)
            FROM Contem c2
            JOIN Produto pr2 ON pr2.id = c2.id.produtoId
            WHERE c2.id.pacoteNome = p.nome
              AND pr2.status = 'Comercializável'
        )
    """)
    public List<MediaAvaliacoesPacoteDto> findMediaAvaliacoesPacote();

    @Query(value = """
        WITH total_produtos AS (
            SELECT\s
                c.pacote_nome,\s
                COUNT(DISTINCT c.produto_id) AS total_produtos
            FROM CONTEM c
            JOIN PRODUTO pr ON pr.id = c.produto_id
            WHERE pr.status = 'Comercializável'
            GROUP BY c.pacote_nome
        ),   
        feedbacks_por_pacote_mes AS (
            SELECT
                p.nome AS pacote,
                date_trunc('month', d.data_envio)::date AS mes,
                COUNT(DISTINCT c.produto_id) AS produtos_avaliados_no_mes,
                AVG(f.avaliacao) AS media_avaliacao
            FROM PACOTE p
            JOIN CONTEM c ON p.nome = c.pacote_nome
            JOIN PRODUTO pr ON pr.id = c.produto_id
            JOIN DEVOLUTIVA d ON d.produto_id = c.produto_id
            JOIN FEEDBACK f ON f.devolutiva_id = d.id
            WHERE pr.status = 'Comercializável'
            GROUP BY p.nome, mes
        )
        
        SELECT
            fpm.pacote,
            fpm.mes AS data,
            ROUND(fpm.media_avaliacao::numeric, 2) AS avaliacao
        FROM feedbacks_por_pacote_mes fpm
        JOIN total_produtos tp ON tp.pacote_nome = fpm.pacote
        WHERE fpm.produtos_avaliados_no_mes = tp.total_produtos
        ORDER BY fpm.pacote, fpm.mes
    """, nativeQuery=true)
    public List<Object[]> findAvaliacaoMensalPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.entity.Pacote(p.nome, p.precoOrganizacional, p.precoIndividual)
        FROM Adquire a
        JOIN Pacote p ON a.id.pacoteNome = p.nome
        GROUP BY p.nome
        ORDER BY COUNT(*) DESC, p.nome
        LIMIT 4
    """)
    public List<Pacote> findPacotesMaisPopulares();
}
