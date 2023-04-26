package com.etoullali;

import com.etoullali.entities.Abonnement;
import com.etoullali.entities.Client;
import com.etoullali.enums.TypeAbonnement;
import com.etoullali.repositories.AbonnementRepository;
import com.etoullali.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean //au démarrage exécute moi cette méthode
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, AbonnementRepository abonnementRepository){
        return args -> {

            List<Abonnement> listAbonnement1=new ArrayList<>();

            Abonnement a1=new Abonnement(),a2=new Abonnement();
            a1.setSolde(Math.random() * 100);
            a1.setDateAbonnement(new Date());
            a1.setTypeAbonnement(TypeAbonnement.GSM);
            a1.setMontantMensuel(Math.random() * 100);
            abonnementRepository.save(a1);

            a2.setSolde(Math.random() * 100);
            a2.setDateAbonnement(new Date());
            a2.setTypeAbonnement(TypeAbonnement.TELEPHONE_FIXE);
            a2.setMontantMensuel(Math.random() * 100);
            abonnementRepository.save(a2);

            listAbonnement1.add(a1);
            listAbonnement1.add(a2);

            /*Stream.of("Ayoub","Samira", "Ihssan", "Radouan", "Ayoub", "Mustapha", "Hayat")
                    .forEach(name -> {
                        Client c = new Client();
                        c.setNom(name);
                        c.setEmail(name+"@gmail.com");
                        c.setUsername(name);
                        c.setAbonnements(null);
                        clientRepository.save(c);
                    });*/

            Client c1 = new Client();
            c1.setNom("Hassan");
            c1.setEmail("Hassan@gmail.com");
            c1.setUsername("Hassan");
            c1.setAbonnements(listAbonnement1);
            clientRepository.save(c1);

            Client c2 = new Client();
            c2.setNom("Salim");
            c2.setEmail("Salim@gmail.com");
            c2.setUsername("Salim");
            c2.setAbonnements(listAbonnement1);
            clientRepository.save(c2);

            a1.setClient(c1);
            a2.setClient(c2);

            abonnementRepository.save(a1);
            abonnementRepository.save(a2);

            //***********************************************************

            System.out.println("\n*** Liste des Clients ***");
            clientRepository.findAll().forEach(c->{
                System.out.println(c.getNom());
            });
            System.out.println("\n*** Liste des Abonnements ***");
            abonnementRepository.findAll().forEach(c->{
                System.out.println(c.getTypeAbonnement());
            });
        };
    }
}
