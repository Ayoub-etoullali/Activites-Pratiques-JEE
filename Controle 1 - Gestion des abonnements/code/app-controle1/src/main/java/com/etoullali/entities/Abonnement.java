package com.etoullali.entities;

import com.etoullali.enums.TypeAbonnement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Abonnements")
@Data @AllArgsConstructor @NoArgsConstructor
public class Abonnement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //pour unifier le format (ex) remplir formulaire
    private Date dateAbonnement;
    private TypeAbonnement typeAbonnement;
    private double solde;
    private double montantMensuel;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
