package com.etoullali.repositories;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
