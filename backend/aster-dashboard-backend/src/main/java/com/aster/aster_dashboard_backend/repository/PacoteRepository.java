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
        SELECT new com.aster.aster_dashboard_backend.dto.VendasMensaisPacoteDto(p.nome, DATE_TRUNC('month', l.dataRegistro), COUNT(*))
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
        
                    WHEN l.tipo = 'Vitalícia'
                         AND l.data_registro <= date_trunc('month', CURRENT_DATE) THEN
                        CASE
                            WHEN i.cliente_documento IS NOT NULL THEN p.preco_individual / 2
                            ELSE p.preco_organizacional / 2
                        END
        
                    ELSE 0
                END
            ), 2) AS receita
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
            ), 2) AS vendas
        FROM meses_licenca m
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = m.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = m.cliente_documento
        WHERE m.ativa = TRUE
        GROUP BY m.pacote_nome, m.mes
        ORDER BY m.pacote_nome, m.mes
    """, nativeQuery = true)
    public List<Object[]> findReceitaMensalPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.MediaAvaliacoesPacoteDto(p.nome, CAST(AVG(f.avaliacao) AS bigdecimal))
        FROM Pacote p
        JOIN Contem c ON p.nome = c.id.pacoteNome
        JOIN Devolutiva d ON c.id.produtoId = d.produto.id
        JOIN Feedback f ON d.id = f.id
        GROUP BY p.nome
    """)
    public List<MediaAvaliacoesPacoteDto> findMediaAvaliacoesPacote();

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.AvaliacaoMensalPacoteDto(p.nome, DATE_TRUNC('month', d.dataEnvio), CAST(AVG(f.avaliacao) AS bigdecimal))
        FROM Pacote p
        JOIN Contem c ON p.nome = c.id.pacoteNome
        JOIN Devolutiva d ON c.id.produtoId = d.produto.id
        JOIN Feedback f ON d.id = f.id
        GROUP BY DATE_TRUNC('month', d.dataEnvio), p.nome
    """)
    public List<AvaliacaoMensalPacoteDto> findAvaliacaoMensalPacote();
}
