package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteOrganizacaoRepository extends JpaRepository<Organizacao, String> {

    @Query(value= """
        WITH base AS (
            SELECT
                setor_atuacao,
                COUNT(*) AS qtd
            FROM ORGANIZACAO
            GROUP BY setor_atuacao
        ),
        marcada AS (
            SELECT
                setor_atuacao,
                qtd,
                ROW_NUMBER() OVER (ORDER BY qtd DESC) AS posicao
            FROM base
        ),
        agrupada AS (
            SELECT
                CASE
                    WHEN posicao > 9 THEN 'Outros'   -- os 9 maiores permanecem, os demais agrupam
                    ELSE setor_atuacao
                END AS setor_agrupado,
                SUM(qtd) AS qtd_final
            FROM marcada
            GROUP BY
                CASE WHEN posicao > 9 THEN 'Outros' ELSE setor_atuacao END
        ),
        total AS (
            SELECT SUM(qtd_final) AS total_geral FROM agrupada
        ),
        final AS (
            SELECT
                setor_agrupado,
                qtd_final,
                100.0 * qtd_final / total_geral AS porcentagem
            FROM agrupada, total
        )
        SELECT
            ROW_NUMBER() OVER (ORDER BY porcentagem DESC) AS id,
            ROUND(porcentagem, 2) AS porcentagem,
            setor_agrupado
        FROM final
        ORDER BY id
    """, nativeQuery = true)
    public List<Object[]> findPorcentagemSetorAtuacao();

    @Query(value= """
        WITH base AS (
            SELECT
                TRIM(porte) AS porte,
                COUNT(*) AS qtd
            FROM organizacao
            GROUP BY TRIM(porte)
        ),
        
        total AS (
            SELECT SUM(qtd) AS total_geral
            FROM base
        ),
        
        com_porcentagem AS (
            SELECT
                porte,
                ROUND(100.0 * qtd / total_geral, 2) AS porcentagem
            FROM base, total
        )
        
        SELECT
            ROW_NUMBER() OVER (ORDER BY porcentagem DESC) AS id,
            porcentagem,
            porte
        FROM com_porcentagem
        ORDER BY id
    """, nativeQuery = true)
    public List<Object[]> findPorcentagemPorte();
}
