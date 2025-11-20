package com.aster.aster_dashboard_backend.repository;

import com.aster.aster_dashboard_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolutivaTicketRepository extends JpaRepository<Ticket, String> {
}
