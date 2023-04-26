package com.etoullali.repositories;

import com.etoullali.entities.Abonnement;
import com.etoullali.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;

public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {
    Collection<Abonnement> findByClientId(Long clientId);
    Collection<Abonnement> findByClient(Client client);
}
