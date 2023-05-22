package com.etoullali.dtos;

import com.etoullali.entities.Aeroport;
import com.etoullali.entities.Pays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class VilleDTO {
    private Long id;
    private String name;
    private AeroportDTO aeroportDTO;
    private PaysDTO paysDTO;
}
