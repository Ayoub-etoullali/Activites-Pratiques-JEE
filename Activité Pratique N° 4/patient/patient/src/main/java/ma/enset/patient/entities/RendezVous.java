package ma.enset.patient.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class RendezVous {
    @Id
    private String id;
    private Date dateRDV;
    private Date heureRDV;
    @Enumerated(EnumType.STRING) // par défaut ORDINAL
    private Statut statut;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //pour la consultation web : c'est pas la peine de lui donnée les autres
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
    @OneToOne
    private Consultation consultation;

}
