package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, String> {
}
