package com.etoullali.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Date dateDepart;
    private Date dateArrivee;
    @ManyToOne
    private Voyage voyage;
    @OneToOne
    private Avion avion;
    @OneToOne
    private Aeroport aeroport;
    @OneToOne
    private Ticket ticket;
}
