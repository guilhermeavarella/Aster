package com.aster.aster_dashboard_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aster.aster_dashboard_backend.entity.Usa;
import com.aster.aster_dashboard_backend.entity.id.UsaId;

public interface UsaRepository extends JpaRepository<Usa, UsaId>{

    
}