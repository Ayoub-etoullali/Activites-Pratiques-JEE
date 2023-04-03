package ma.enset.appspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(length = 20,unique = true)
    private  String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name="USERS_ROLES")
    @ToString.Exclude //on va pas returner la liste utilisateurs (on va tomber dans une boucle infini (User) <--> (Role)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //property (cas de bidirectionnel)
    private List<User> users=new ArrayList<>();

}
