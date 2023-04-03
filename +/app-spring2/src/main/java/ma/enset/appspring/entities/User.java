package ma.enset.appspring.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private  String userId;
    @Column(name = "USER_NAME",unique = true,length = 20) //les majuscules coté requete SQL
    private  String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //property
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();

}
