package com.etoullali.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Reservation reservation;
    @OneToMany(mappedBy = "voyage")
    private List<Passager> passagers;
    @OneToMany(mappedBy = "voyage")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "voyage")
    private List<Vol> vols;
}
