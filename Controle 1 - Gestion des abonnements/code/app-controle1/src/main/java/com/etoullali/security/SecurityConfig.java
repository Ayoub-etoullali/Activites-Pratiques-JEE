package com.etoullali.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        return new InMemoryUserDetailsManager(
                User.withUsername("ayoub").password(passwordEncoder().encode("ayoub")).roles("CLIENT").build(), //d'une manière simple on utilise "{noop}1234" | <noop> cad pas utiliser encoder (hash)
                User.withUsername("rado").password(passwordEncoder().encode("rado")).roles("CLIENT").build(),
                User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("CLIENT","ADMIN").build()
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
        httpSecurity.authorizeHttpRequests().requestMatchers("/client/**").hasRole("CLIENT");
        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();
    }
}