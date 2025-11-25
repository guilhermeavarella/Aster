package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteIndividualRepository extends JpaRepository<Individual, String> {

    @Query(value= """
        WITH base AS (
            SELECT
                TRIM(atividade_uso) AS atividade_uso,
                COUNT(*) AS qtd
            FROM individual
            GROUP BY TRIM(atividade_uso)
        ),
        
        num_categorias AS (
            SELECT COUNT(*) AS total FROM base
        ),
        
        ordenada AS (
            SELECT
                atividade_uso,
                qtd,
                ROW_NUMBER() OVER (ORDER BY qtd DESC) AS pos
            FROM base
        ),
        
        marcada AS (
            SELECT
                CASE
                    WHEN (SELECT total FROM num_categorias) >= 10 AND pos >= 10
                        THEN 'Outros'
                    ELSE atividade_uso
                END AS atividade_uso_agrupada,
                qtd
            FROM ordenada
        ),
        
        agrupada AS (
            SELECT
                atividade_uso_agrupada,
                SUM(qtd) AS qtd_final
            FROM marcada
            GROUP BY atividade_uso_agrupada
        ),
        
        total AS (
            SELECT SUM(qtd_final) AS total_geral FROM agrupada
        ),
        
        com_porcentagem AS (
            SELECT
                atividade_uso_agrupada,
                ROUND(100.0 * qtd_final / total_geral, 2) AS porcentagem
            FROM agrupada, total
        ),
        
        nao_outros AS (
            SELECT
                atividade_uso_agrupada,
                porcentagem,
                ROW_NUMBER() OVER (ORDER BY porcentagem DESC) AS id
            FROM com_porcentagem
            WHERE atividade_uso_agrupada <> 'Outros'
        ),
        
        outros AS (
            SELECT
                10 AS id,
                porcentagem,
                atividade_uso_agrupada
            FROM com_porcentagem
            WHERE atividade_uso_agrupada = 'Outros'
        )
        
        SELECT id, porcentagem, atividade_uso_agrupada AS atividade_uso
        FROM nao_outros
        
        UNION ALL
        
        SELECT id, porcentagem, atividade_uso_agrupada
        FROM outros
        
        ORDER BY id
    """, nativeQuery = true)
    public List<Object[]> findPorcentagemAtividadeUso();
}
