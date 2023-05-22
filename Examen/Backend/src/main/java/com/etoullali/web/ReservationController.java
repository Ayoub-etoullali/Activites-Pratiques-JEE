package com.etoullali.web;

import com.etoullali.dtos.ClientDTO;
import com.etoullali.dtos.ReservationDTO;
import com.etoullali.dtos.VolDTO;
import com.etoullali.services.ReservationService;
import com.etoullali.services.VolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ReservationController {
    private ReservationService reservationService;

    @GetMapping("/reservations/all")
    public List<ReservationDTO> Reservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public ReservationDTO getReservation(@PathVariable(name = "id") Long id){
        return reservationService.getReservationById(id);
    }

}
