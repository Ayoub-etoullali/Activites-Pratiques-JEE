package com.etoullali.dtos;

import com.etoullali.entities.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class PaysDTO {
    private Long id;
    private String name;
    private VilleDTO villeDTO;
}
