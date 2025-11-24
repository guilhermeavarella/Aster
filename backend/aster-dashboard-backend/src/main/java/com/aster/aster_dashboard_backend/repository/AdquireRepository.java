package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.ReceitaTotalDto;
import com.aster.aster_dashboard_backend.entity.Adquire;
import com.aster.aster_dashboard_backend.entity.id.AdquireId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdquireRepository extends JpaRepository<Adquire, AdquireId> {

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ReceitaTotalDto(DATE_TRUNC('month', l.dataRegistro), CAST((SUM(pct.precoIndividual) + SUM(pct.precoOrganizacional)) AS bigdecimal))
        FROM Adquire a
        JOIN Licenca l ON a.id.licencaId = l.id
        JOIN Pacote pct ON a.id.pacoteNome = pct.nome
        GROUP BY DATE_TRUNC('month', l.dataRegistro)
    """)
    public List<ReceitaTotalDto> findReceitaTotal();
    
}
