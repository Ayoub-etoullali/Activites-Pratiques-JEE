package ma.enset.appspring;

import ma.enset.appspring.entities.Role;
import ma.enset.appspring.entities.User;
import ma.enset.appspring.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class AppSpring2Application {

    public static void main(String[] args) {
        SpringApplication.run(AppSpring2Application.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                User u=new User();
                u.setUsername("user1");
                u.setPassword("24dfgfdgfegrfd41");
                userService.addNewUser(u);

                User u2=new User();
                u2.setUsername("admin");
                u2.setPassword("24dfgfdgfegrfd41");
                userService.addNewUser(u2);

                Stream.of("STUDENT","ADMIN","USER").forEach(r->{
                    Role role1=new Role();
                    role1.setRoleName(r);
                    userService.addNewRole(role1);
                });

                userService.addRoleToUser("user1","STUDENT");
                userService.addRoleToUser("user1","USER");
                userService.addRoleToUser("admin","ADMIN");
                userService.addRoleToUser("admin","USER");

                try {
                    User user=userService.authentificate("user1","24dfgfdgfegrfd41");
                    System.out.println(user.getUserId());
                    System.out.println(user.getUsername());
                    System.out.println("Role : ");
                    user.getRoles().forEach(r->{
                        System.out.println("=> "+r);
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }
}
