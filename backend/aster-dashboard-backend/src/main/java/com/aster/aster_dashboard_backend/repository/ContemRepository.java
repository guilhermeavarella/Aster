package com.aster.aster_dashboard_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aster.aster_dashboard_backend.entity.Contem;
import com.aster.aster_dashboard_backend.entity.id.ContemId;

public interface ContemRepository extends JpaRepository<Contem, ContemId>{
    
}
