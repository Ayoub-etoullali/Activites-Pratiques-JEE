package com.etoullali.repositories;

import com.etoullali.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface ClientRepository extends JpaRepository<Client,Long> {

    Page<Client> findByNomContains(String keyword , Pageable pageable);
    Optional<Client> findByUsername(String username2);
    List<Client> findByNom(String nom1);
}
