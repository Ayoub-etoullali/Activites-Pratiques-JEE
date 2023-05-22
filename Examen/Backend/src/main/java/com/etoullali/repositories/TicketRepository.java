package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
