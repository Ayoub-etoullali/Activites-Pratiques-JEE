package com.etoullali.services;

import com.etoullali.entities.Client;
import com.etoullali.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
//    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Client> findByNomClientContaining(String kw, Pageable pageable) {
        return clientRepository.findByNomContains(kw,pageable);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
