package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.TotalVendasPacoteDto;
import com.aster.aster_dashboard_backend.dto.VendasMensaisPacoteDto;
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
}
