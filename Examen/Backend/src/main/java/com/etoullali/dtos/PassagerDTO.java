package com.etoullali.dtos;

import com.etoullali.entities.Voyage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class PassagerDTO {
    private Long id;
    private String nom;
    private String email;
    private VoyageDTO voyageDTO;
    private TicketDTO ticketDTO;
}
