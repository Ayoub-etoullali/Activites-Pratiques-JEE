package com.etoullali.services;

import com.etoullali.entities.Abonnement;
import com.etoullali.entities.Client;
import com.etoullali.repositories.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface AbonnementService {

    Collection<Abonnement> getAllAbonnements();

    Optional<Abonnement> getAbonnementById(Long id);

    void saveAbonnement(Abonnement abonnement);

    void deleteAbonnementById(Long id);

    Collection<Abonnement> findAbonnementsByClientId(Long clientId);

    void rechargerSolde(Long abonnementId, double montant);

    Collection<Abonnement> findAbonnementsByClient(Client client);
}
