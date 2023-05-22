package com.etoullali.dtos;

import com.etoullali.entities.Reservation;
import com.etoullali.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
public class VoyageDTO {
    private Long id;
    private ReservationDTO reservationDTO;
}
