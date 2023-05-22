package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {

}
