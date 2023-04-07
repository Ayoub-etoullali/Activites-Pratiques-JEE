package ma.enset.patient.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor //@Data il génère @NoArgsConstructor en private
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty //Validation
    @Size(min = 4,max = 40) //Validation
    private String nom;
    //    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //pour unifier le format (ex) remplir formulaire
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("0") //Validation
    @DecimalMax("100") //Validation
    private int score;

}
