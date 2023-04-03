package ma.enset.patient.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

//    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ayoub").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("samira").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("hayat").password("1234").roles("USER", "ADMIN");
        /*
        => autre méthode

        auth.inMemoryAuthentication()
                .withUser("ayoub").password("1234").roles("USER")
                .and()
                .withUser("samira").password("1234").roles("USER")
                .and()
                .withUser("hayat").password("1234").roles("USER", "ADMIN");
        */
    }
//    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin(); //formLogin() : formulaire d'authentification
                            //pour créer votre formulaire, utiliser loginPage()
        http.authorizeHttpRequests().anyRequest().authenticated(); //les droits d'accées
                                                                    //anyRequest().authenticated() : toutes les requetes HTTP nécessite une auth
    }
}