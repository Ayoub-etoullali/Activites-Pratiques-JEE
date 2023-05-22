package com.etoullali.dtos;

import com.etoullali.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
}
