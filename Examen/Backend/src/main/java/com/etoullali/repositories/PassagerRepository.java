package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Passager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassagerRepository extends JpaRepository<Passager, Long> {

}
