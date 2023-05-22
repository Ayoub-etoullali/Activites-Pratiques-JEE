package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Long> {

}
