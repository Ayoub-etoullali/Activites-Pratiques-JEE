package com.etoullali.dtos;

import com.etoullali.entities.Vol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class AvionDTO {
    private Long id;
    private String nom;
    private int nmrPlace;
    private VolDTO volDTO;
}
