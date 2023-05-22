package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvionRepository extends JpaRepository<Avion, Long> {

}
