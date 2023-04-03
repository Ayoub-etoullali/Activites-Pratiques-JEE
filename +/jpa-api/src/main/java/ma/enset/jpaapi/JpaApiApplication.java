package ma.enset.jpaapi;

import ma.enset.jpaapi.entities.Patient;
import ma.enset.jpaapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApiApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApiApplication.class, args);
    }
/*  => autre méthode

    @Bean //dans Spring, c'est on a une méthode qui utilise l'annotation Bean :
                -> c'est une méthode qui exécuté aux démarrage (en mémoire)
                -> cette méthode return un objet (devient un composant Spring placer dans un "contexte"
                    (va le mettre parmi la liste des objets des composants qui créer) => il suffit
                    d'utiliser @Autowired pour injecter aux vous voulez

    CommandLineRunner start(){ // metter un objet => injection de dépendance
        return args -> {

        }
    }
 */
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 50; i++) {
            patientRepository.save(
                    new Patient(null, "Ayoub", new Date(), Math.random()>0.5?true:false, (int) (Math.random() * 100)));
        }
        patientRepository.save(
                new Patient(null, "Hayat", new Date(), true, 40));
        patientRepository.save(
                new Patient(null, "Ihssan", new Date(), false, 80));
        patientRepository.save(
                new Patient(null, "Karima", new Date(), true, 60));
        patientRepository.save(
                new Patient(null, "Samira", new Date(), false, 90));

/*
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p->{
            System.out.println("--------------------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
        });
 */
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0, 5)); //PageRequest = par page
        System.out.println("Total pages = " + patients.getTotalPages());
        System.out.println("Total elements = " + patients.getTotalElements());
        System.out.println("Num pages = " + patients.getNumber());

        Page<Patient> malade = patientRepository.findByMalade(false,PageRequest.of(0, 5));
        List<Patient> contents = patients.getContent();
//        List<Patient> patientList = patientRepository.ChercherPatient(new Date(2023/3/10),new Date(2023/3/20),"%a%");

        contents.forEach(p -> {
            System.out.println("--------------------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
        });

        System.out.println("#####################################");
         /*
         patientRepository.findById(1L).get(); // get() = orElseThrow(()-> new RuntimeException("Patient not found") || 1L = new Long(1)
          */
        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient != null) {
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
            System.out.println(patient.getScore());
        }
        patient.setScore(45);
        patientRepository.save(patient);
        //patientRepository.delete(patient);
    }
}
