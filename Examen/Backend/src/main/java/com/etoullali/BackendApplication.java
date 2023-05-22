package com.etoullali;

import com.etoullali.dtos.*;
import com.etoullali.enums.Type;
import com.etoullali.services.ReservationService;
import com.etoullali.services.VolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(VolService volService,
                                        ReservationService reservationService) {
        return args -> {

            Stream.of("ayoub", "hayat", "samira", "mustapha", "karima", "radouan").forEach(name -> {
                        ClientDTO client1 = new ClientDTO();
                        client1.setPrenom(name);
                        client1.setNom("ETOULLALI");
                        client1.setEmail(name + "@gmail.com");
                        volService.saveClient(client1);
                    });

            Stream.of("vol 1", "vol 2", "vol 3").forEach(name -> {
                VolDTO volDTO = new VolDTO();
                volDTO.setNom(name);
                volDTO.setDateDepart(new Date());
                volDTO.setDateArrivee(new Date());
                volService.saveVol(volDTO);
            });

            Stream.of("exemple1", "exemple2").forEach(name -> {
                ReservationDTO reservationDTO = new ReservationDTO();
                reservationDTO.setSiteReservation("www."+name+".com");
                reservationService.saveReservation(reservationDTO);
            });

            Stream.of("exemple1", "exemple2").forEach(name -> {
                VoyageDTO voyageDTO = new VoyageDTO();
                volService.saveVoyage(voyageDTO);
            });

            Stream.of("passager 1", "passager 2","passager 3").forEach(name -> {
                PassagerDTO passagerDTO = new PassagerDTO();
                passagerDTO.setNom(name);
                passagerDTO.setEmail(name+"@gmail.com");
                volService.savePassager(passagerDTO);
            });

            Stream.of("1", "2","3").forEach(i -> {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setPrenom(Type.classe1);
                ticketDTO.setNumeroPlace(Integer.parseInt(i));
                volService.saveTicket(ticketDTO);
            });

            System.out.println("\n </> By Ayoub ETOULLALI \n");
        };
    }
}