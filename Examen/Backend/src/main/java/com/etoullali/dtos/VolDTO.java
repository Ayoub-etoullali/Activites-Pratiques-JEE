package com.etoullali.dtos;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Avion;
import com.etoullali.entities.Voyage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
public class VolDTO {
    private Long id;
    private String nom;
    private Date dateDepart;
    private Date dateArrivee;
    private VoyageDTO voyageDTO;
    private AvionDTO avionDTO;
    private AeroportDTO aeroportDTO;
}
