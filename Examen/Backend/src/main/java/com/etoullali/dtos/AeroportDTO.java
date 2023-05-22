package com.etoullali.dtos;

import com.etoullali.entities.Ville;
import com.etoullali.entities.Vol;
import com.etoullali.enums.PositionGeographique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class AeroportDTO {
    private Long id;
    private String nom;
    private PositionGeographique positionGeographique;
    private VolDTO volDTO;
    private VilleDTO villeDTO;

}
