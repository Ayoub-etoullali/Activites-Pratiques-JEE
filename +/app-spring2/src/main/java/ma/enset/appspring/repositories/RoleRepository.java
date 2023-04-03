package ma.enset.appspring.repositories;

import ma.enset.appspring.entities.Role;
import ma.enset.appspring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);

}
