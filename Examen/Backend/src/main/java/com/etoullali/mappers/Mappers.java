package com.etoullali.mappers;

import com.etoullali.dtos.*;
import com.etoullali.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Mappers {

    public Aeroport fromAeroportDTO(AeroportDTO aeroportDTO) {
        Aeroport aeroport = new Aeroport();
        BeanUtils.copyProperties(aeroportDTO,aeroport);
        return aeroport;
    }
    public AeroportDTO fromAeroport(Aeroport aeroport) {
        AeroportDTO aeroportDTO = new AeroportDTO();
        BeanUtils.copyProperties(aeroport,aeroportDTO);
        return aeroportDTO;
    }

    public Avion fromAvionDTO(AvionDTO avionDTO) {
        Avion avion = new Avion();
        BeanUtils.copyProperties(avionDTO,avion);
        return avion;
    }
    public AvionDTO fromAvion(Avion avion) {
        AvionDTO avionDTO = new AvionDTO();
        BeanUtils.copyProperties(avion,avionDTO);
        return avionDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }
    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }

    public Passager fromPassagerDTO(PassagerDTO passagerDTO) {
        Passager passager = new Passager();
        BeanUtils.copyProperties(passagerDTO,passager);
        return passager;
    }
    public PassagerDTO fromPassager(Passager passager) {
        PassagerDTO passagerDTO = new PassagerDTO();
        BeanUtils.copyProperties(passager,passagerDTO);
        return passagerDTO;
    }

    public Pays fromPaysDTO(PaysDTO paysDTO) {
        Pays pays = new Pays();
        BeanUtils.copyProperties(paysDTO,pays);
        return pays;
    }
    public PaysDTO fromPays(Pays pays) {
        PaysDTO paysDTO = new PaysDTO();
        BeanUtils.copyProperties(pays,paysDTO);
        return paysDTO;
    }

    public Reservation fromReservationDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationDTO,reservation);
        return reservation;
    }
    public ReservationDTO fromReservation(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation,reservationDTO);
        return reservationDTO;
    }

    public Ticket fromTicketDTO(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketDTO,ticket);
        return ticket;
    }
    public TicketDTO fromTicket(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        BeanUtils.copyProperties(ticket,ticketDTO);
        return ticketDTO;
    }

    public Ville fromVilleDTO(VilleDTO villeDTO) {
        Ville ville = new Ville();
        BeanUtils.copyProperties(villeDTO,ville);
        return ville;
    }
    public VilleDTO fromVille(Ville ville) {
        VilleDTO villeDTO = new VilleDTO();
        BeanUtils.copyProperties(ville,villeDTO);
        return villeDTO;
    }

    public Vol fromVolDTO(VolDTO volDTO) {
        Vol vol = new Vol();
        BeanUtils.copyProperties(volDTO,vol);
        return vol;
    }
    public VolDTO fromVol(Vol vol) {
        VolDTO volDTO = new VolDTO();
        BeanUtils.copyProperties(vol,volDTO);
        return volDTO;
    }

    public Voyage fromVoyageDTO(VoyageDTO voyageDTO) {
        Voyage voyage = new Voyage();
        BeanUtils.copyProperties(voyageDTO,voyage);
        return voyage;
    }
    public VoyageDTO fromVoyage(Voyage voyage) {
        VoyageDTO voyageDTO = new VoyageDTO();
        BeanUtils.copyProperties(voyage,voyageDTO);
        return voyageDTO;
    }
}
