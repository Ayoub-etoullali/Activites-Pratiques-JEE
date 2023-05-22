package com.etoullali.dtos;

import com.etoullali.entities.Vol;
import com.etoullali.entities.Voyage;
import com.etoullali.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class TicketDTO {
    private Long id;
    private int numeroPlace;
    private Type prenom;
    private VoyageDTO voyageDTO;
    private VolDTO volDTO;
}
