package ma.enset.appspring.repositories;

import ma.enset.appspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository //meme role de @Service dans repository services (ca
            // < is used to indicate that the class provides the mechanism for storage,
            // retrieval, search, update and delete operation on objects.>

//pour le web : @RestController
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
