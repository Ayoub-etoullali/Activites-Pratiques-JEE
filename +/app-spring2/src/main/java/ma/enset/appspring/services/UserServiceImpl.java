package ma.enset.appspring.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.enset.appspring.entities.Role;
import ma.enset.appspring.entities.User;
import ma.enset.appspring.repositories.RoleRepository;
import ma.enset.appspring.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

// @Component : indiquer à Spring que cette interface démarrer aux démarrage
@Service // ^ pour la couche dao
@Transactional
@AllArgsConstructor //Spring ne doit pas avoir deux constructeurs (@NoArgsConstructor),
                    // parce qu'il fait l'injecion juste dans une seule constructeur

public class UserServiceImpl implements UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
            User user=findUserByUserName(username);
            Role role=findRoleByRoleName(roleName);
            if(user.getRoles()!=null){
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
            //dans une association bidirectionnel => automatiquement on a l'affectation
            // cad c'est pas la peine de m-à-j user :  userRepository.save(user)

    }

    @Override
    public User authentificate(String username, String password) {
        User user=findUserByUserName(username);
        if (user==null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
