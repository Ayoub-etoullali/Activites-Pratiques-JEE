package ma.enset.patient.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("ayoub").password("{noop}1234").roles("USER").build(), //noop : pas utiliser encoder
                User.withUsername("Rado").password("{noop}1234").roles("USER").build(),
                User.withUsername("hayat").password("{noop}1234").roles("USER", "ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }
}

    /*
//    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ayoub").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("samira").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("hayat").password("1234").roles("USER", "ADMIN");
        /
        => autre méthode

        auth.inMemoryAuthentication()
                .withUser("ayoub").password("1234").roles("USER") //noop : pas utiliser encoder
                .and()
                .withUser("samira").password("1234").roles("USER")
                .and()
                .withUser("hayat").password("1234").roles("USER", "ADMIN");
        /
    }
//    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin(); //formLogin() : formulaire d'authentification
                            //pour créer votre formulaire, utiliser loginPage()
        http.authorizeHttpRequests().anyRequest().authenticated(); //les droits d'accées
                                                                    //anyRequest().authenticated() : toutes les requetes HTTP nécessite une auth
    }
     */
