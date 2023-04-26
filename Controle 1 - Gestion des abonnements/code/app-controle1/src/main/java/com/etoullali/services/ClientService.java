package com.etoullali.services;

import com.etoullali.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Page<Client> findByNomClientContaining(String kw, Pageable pageable);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);

    void saveClient(Client client);
    void deleteClientById(Long id) ;
}
