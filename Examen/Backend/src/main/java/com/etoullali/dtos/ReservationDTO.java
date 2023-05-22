package com.etoullali.dtos;

import com.etoullali.entities.Client;
import com.etoullali.entities.Voyage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;
    private Date date;
    private String siteReservation;
    private ClientDTO clientDTO;
    private VoyageDTO voyageDTO;
}
