package com.etoullali.services;

import com.etoullali.dtos.*;
import com.etoullali.entities.*;
import com.etoullali.mappers.Mappers;
import com.etoullali.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class VolServiceImpl implements VolService {
    private VolRepository volRepository;
    private TicketRepository ticketRepository;
    private PassagerRepository passagerRepository;
    private VoyageRepository voyageRepository;
    private ClientRepository clientRepository;
    private Mappers mapper;
    @Override
    public VolDTO getVolById(Long id) {
        return mapper.fromVol(volRepository.findById(id).orElse(null));
    }

    @Override
    public List<VolDTO> getAllVols() {
        List<Vol> vols = volRepository.findAll();
        List<VolDTO> volDTOS = vols
                .stream()
                .map(vol -> mapper.fromVol(vol))
                .collect(Collectors.toList());
        return volDTOS;
    }

    @Override
    public void saveVol(VolDTO volDTO) {
        Vol vol=mapper.fromVolDTO(volDTO);
        volRepository.save(vol);
    }

    @Override
    public void saveClient(ClientDTO client1) {
        Client client=mapper.fromClientDTO(client1);
        clientRepository.save(client);
    }



    @Override
    public void saveVoyage(VoyageDTO voyageDTO) {
        Voyage voyage=mapper.fromVoyageDTO(voyageDTO);
        voyageRepository.save(voyage);
    }

    @Override
    public void savePassager(PassagerDTO passagerDTO) {
        Passager passager=mapper.fromPassagerDTO(passagerDTO);
        passagerRepository.save(passager);
    }

    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        Ticket ticket=mapper.fromTicketDTO(ticketDTO);
        ticketRepository.save(ticket);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOS = clients
                .stream()
                .map(client -> mapper.fromClient(client))
                .collect(Collectors.toList());
        return clientDTOS;
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return mapper.fromClient(clientRepository.findById(id).orElse(null));

    }

    @Override
    public List<VolDTO> searchVol(String keyword) {
        List<Vol> vols=volRepository.findByNomContains(keyword);
        List<VolDTO> volDTOS = vols.stream().map(vol -> mapper.fromVol(vol)).collect(Collectors.toList());
        return volDTOS;
    }

}
