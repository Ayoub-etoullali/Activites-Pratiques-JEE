package com.etoullali.entities;

import com.etoullali.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroPlace;
    private Type prenom;
    @ManyToOne
    private Voyage voyage;
    @OneToOne
    private Vol vol;
    @OneToOne
    private Passager passager;
}
