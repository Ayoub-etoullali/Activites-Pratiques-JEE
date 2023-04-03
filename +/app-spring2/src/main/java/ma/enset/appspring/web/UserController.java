package ma.enset.appspring.web;

import ma.enset.appspring.entities.User;
import ma.enset.appspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //is used to create RESTful web services using Spring MVC.
                // Spring RestController takes care of mapping request data to the defined request handler method.
                // Once response body is generated from the handler method, it converts it to JSON or XML response.
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}") //consulter la liste des users | user ***** paramètre useername
    public User user(@PathVariable String username){ //cette username represente le paramètre qui vient dans le path
        User user=userService.findUserByUserName(username);
        return user;
    }
}
