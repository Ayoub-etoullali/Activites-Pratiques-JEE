package ma.enset.patient;

import ma.enset.patient.entities.Patient;
import ma.enset.patient.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {

            Patient p1 = new Patient();
            p1.setNom("Sana");
            p1.setDateNaissance(new Date());
            p1.setMalade(true);
            patientRepository.save(p1);

            Stream.of("Samira", "Ihssan", "Radouan", "Ayoub", "Mustapha", "Hayat")
                    .forEach(name -> {
                        Patient patient = new Patient();
                        patient.setNom(name);
                        patient.setDateNaissance(new Date());
                        patient.setMalade(false);
                        patientRepository.save(patient);
                    });

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
}
