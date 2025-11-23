package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.dto.ClientesPaisDto;
import com.aster.aster_dashboard_backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    long countByContinente(String continente);

    @Query("""
        SELECT new com.aster.aster_dashboard_backend.dto.ClientesPaisDto(c.regiao, COUNT(*))
        FROM Cliente c
        GROUP BY c.regiao
    """)
    public List<ClientesPaisDto> countByRegiao();
}
