package com.etoullali.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "clients")
@Data @AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotEmpty //Validation
//    @Size(min = 3,max = 20) //Validation
    private String nom;
//    @NotEmpty
//    @Size(min = 10,max = 40)
    private String email;
//    @NotEmpty
//    @Size(min = 3,max = 15)
    private String username;
    @OneToMany(mappedBy="client")
    private Collection<Abonnement> abonnements;

}
