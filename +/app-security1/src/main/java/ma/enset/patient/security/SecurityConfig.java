package ma.enset.patient.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // [M2] sécurité coté serveur mais n'oublie pas @PreAuthorize dans le controller
public class SecurityConfig {

    //users
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        String password="1234";
        String encodedPWD=passwordEncoder().encode(password);
        System.out.println("-----------------------------");
        System.out.println(encodedPWD);
        System.out.println("-----------------------------");

        // [N.B] Authentification depuis base de donnée
        // vidéo : Part 3 - Spring Boot Spring MVC Thymeleaf- Spring Securiry (1h19min)

        return new InMemoryUserDetailsManager(
                User.withUsername("ayoub").password(passwordEncoder().encode(password)).roles("USER").build(), //d'une manière simple on utilise "{noop}1234" | <noop> cad pas utiliser encoder (hash)
                User.withUsername("Rado").password(passwordEncoder().encode(password)).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode(password)).roles("USER","ADMIN").build()
        );
    }

    @Bean
    PasswordEncoder passwordEncoder(){ //password encoder
        return new BCryptPasswordEncoder(); // nouveau algo très puissant peut faire le hashage de mot de passe
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().loginPage("/login").permitAll(); //formulaire d'auth
        httpSecurity.rememberMe();
        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();

        // [M1] sécurité coté serveur
        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }
}