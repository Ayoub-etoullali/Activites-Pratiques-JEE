package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolRepository extends JpaRepository<Vol, Long> {

    List<Vol> findByNomContains(String keyword);
}
