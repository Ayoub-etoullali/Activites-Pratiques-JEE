package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
