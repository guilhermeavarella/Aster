package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Adquire;
import com.aster.aster_dashboard_backend.entity.id.AdquireId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdquireRepository extends JpaRepository<Adquire, AdquireId> {

    @Query(value = """
        WITH meses_licenca AS (
            SELECT
                l.id AS licenca_id,
                l.tipo,
                l.data_registro,
                l.ativa,
                a.cliente_documento,
                p.preco_individual,
                p.preco_organizacional,
        
                generate_series(
                    date_trunc('month', l.data_registro),
                    LEAST(
                        CASE
                            WHEN l.tipo = 'Mensal'   THEN date_trunc('month', l.data_registro)
                            WHEN l.tipo = 'Anual'    THEN date_trunc('month', l.data_registro) + interval '11 month'
                            WHEN l.tipo = 'Vitalícia' THEN date_trunc('month', CURRENT_DATE)
                            ELSE NULL
                        END,
                        date_trunc('month', CURRENT_DATE)
                    ),
                    interval '1 month'
                ) AS mes
            FROM LICENCA l
            JOIN ADQUIRE a ON a.licenca_id = l.id
            JOIN PACOTE p ON p.nome = a.pacote_nome
            WHERE l.ativa = TRUE
              AND l.tipo <> 'Demo'
        )
        
        SELECT
            m.mes::date AS data,
            ROUND( SUM(
                CASE
                    WHEN m.tipo = 'Mensal' THEN
                        CASE WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                             ELSE m.preco_organizacional
                        END
        
                    WHEN m.tipo = 'Anual' THEN
                        CASE WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual
                             ELSE m.preco_organizacional
                        END
        
                    WHEN m.tipo = 'Vitalícia' THEN
                        CASE WHEN i.cliente_documento IS NOT NULL THEN m.preco_individual / 2
                             ELSE m.preco_organizacional / 2
                        END
        
                    ELSE 0
                END
            )::numeric, 2) AS receita
        FROM meses_licenca m
        LEFT JOIN INDIVIDUAL i ON i.cliente_documento = m.cliente_documento
        LEFT JOIN ORGANIZACAO o ON o.cliente_documento = m.cliente_documento
        GROUP BY m.mes
        ORDER BY m.mes
    """, nativeQuery = true)
    public List<Object[]> findReceitaTotal();
    
}
