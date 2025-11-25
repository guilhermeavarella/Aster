package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, String> {

    long countByAtiva(Boolean ativa);

    @Query(value= """
        WITH contagem AS (
            SELECT
                COUNT(*) AS total,
                SUM(CASE WHEN ativa = TRUE THEN 1 ELSE 0 END) AS ativas,
                SUM(CASE WHEN ativa = FALSE THEN 1 ELSE 0 END) AS inativas
            FROM LICENCA
        )
        SELECT 0 AS id,
               ROUND((ativas::numeric / total * 100), 2) AS value,
               'Licenças Ativas' AS label
        FROM contagem
        UNION ALL
        SELECT 1 AS id,
               ROUND((inativas::numeric / total * 100), 2) AS value,
               'Licenças Inativas' AS label
        FROM contagem
    """, nativeQuery=true)
    public List<Object[]> findPorcentagemAtiva();

}
