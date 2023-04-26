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

public class AbonnementServiceImpl implements AbonnementService {
//    @Autowired
    private AbonnementRepository abonnementRepository;

    public Collection<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    public Optional<Abonnement> getAbonnementById(Long id) {
        return abonnementRepository.findById(id);
    }

    public void saveAbonnement(Abonnement abonnement) {
        abonnementRepository.save(abonnement);
    }

    public void deleteAbonnementById(Long id) {
        abonnementRepository.deleteById(id);
    }

    public Collection<Abonnement> findAbonnementsByClientId(Long clientId) {
        return abonnementRepository.findByClientId(clientId);
    }

    public void rechargerSolde(Long abonnementId, double montant) {
        Optional<Abonnement> optionalAbonnement = abonnementRepository.findById(abonnementId);
        if (optionalAbonnement.isPresent()) {
            Abonnement abonnement = optionalAbonnement.get();
            double nouveauSolde = abonnement.getSolde() + montant;
            abonnement.setSolde(nouveauSolde);
            abonnementRepository.save(abonnement);
        } else {
            throw new NoSuchElementException("L'abonnement avec l'ID " + abonnementId + " n'existe pas");
        }
    }

    @Override
    public Collection<Abonnement> findAbonnementsByClient(Client client) {
        return abonnementRepository.findByClient(client);
    }
}
