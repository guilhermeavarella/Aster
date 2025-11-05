package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Devolutiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolutivaRepository extends JpaRepository<Devolutiva, Integer> {
}
