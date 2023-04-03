package ma.enset.jpaapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity //Indique que la classe est persistante est correspond à une table dans la base de donée
@Data //Lombok : getters et setters
@NoArgsConstructor //Constructeur sans paramètre
@AllArgsConstructor //Constructeur avec tous les paramètres

public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50)
    private  String  nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
