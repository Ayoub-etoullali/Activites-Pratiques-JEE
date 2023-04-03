package ma.enset.appspring.services;

import ma.enset.appspring.entities.Role;
import ma.enset.appspring.entities.User;

public interface UserService {
    User addNewUser (User user);
    Role addNewRole(Role role);
    User findUserByUserName(String roleName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authentificate(String username, String password);
}
