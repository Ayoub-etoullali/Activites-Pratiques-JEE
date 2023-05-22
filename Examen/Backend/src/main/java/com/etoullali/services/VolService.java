package com.etoullali.services;

import com.etoullali.dtos.*;
import com.etoullali.entities.Vol;

import java.util.List;

public interface VolService {
    VolDTO getVolById(Long id);
    List<VolDTO> getAllVols();

    void saveVol(VolDTO volDTO);
    void saveClient(ClientDTO client1);


    void saveVoyage(VoyageDTO voyageDTO);

    void savePassager(PassagerDTO passagerDTO);

    void saveTicket(TicketDTO ticketDTO);

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    List<VolDTO> searchVol(String keyword);
}
