package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Long> {

}
