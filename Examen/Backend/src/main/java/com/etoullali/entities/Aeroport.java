package com.etoullali.entities;

import com.etoullali.enums.PositionGeographique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aeroport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private PositionGeographique positionGeographique;
    @OneToOne
    private Vol vol;
    @OneToOne
    private Ville ville;

}
