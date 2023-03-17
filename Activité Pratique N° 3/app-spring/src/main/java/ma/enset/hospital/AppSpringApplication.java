package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.services.InterfaceHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class AppSpringApplication {
//    @Autowired
//    private  *PatientRepository (patientRepository=);

    public static void main(String[] args) {
        SpringApplication.run(AppSpringApplication.class, args);
    }

/*
    @Bean dans Spring, c'est on a une méthode qui utilise l'annotation Bean :
                -> c'est une méthode qui exécuté aux démarrage de l'application(en mémoire)
                -> cette méthode return un objet (devient un composant Spring placer dans un "contexte"
                   (va le mettre parmi la liste des objets des composants qui créer) => il suffit
                    d'utiliser @Autowired pour injecter aux vous voulez
 */
    /*

    CommandLineRunner start(PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository,
                            ConsultationRepository consultationRepository) { // metter un objet => injection de dépendance
                                                                    // cad n'est pas besoin de déclarer l'objet (voir au desus) en Autowired
*/
    @Bean
    CommandLineRunner start(InterfaceHospitalService hospitalService, PatientRepository patientRepository, RendezVousRepository rendezVousRepository) { // PatientRepository, RendezVousRepository juste pour appel à autre méthode
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Medecin medecin_0 = new Medecin();
                medecin_0.setNom("Hamid");
                medecin_0.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");
                medecin_0.setRendezVous(null);

                Stream.of("Ismail", "Hanane", "Sara")
                        .forEach(name -> {
                            Medecin medecin = new Medecin();
                            medecin.setNom(name);
                            medecin.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");
                            medecin.setRendezVous(null);
                            hospitalService.saveMedecin(medecin); //medecinRepository.save(medecin); cas de sans interface dao
                        });

                // => Méthode classique de déclaration

                Patient p2 = new Patient(null, "Ismail", new Date(), true, null);

                Patient p1 = new Patient();
                p1.setNom("Sana");
                p1.setDateNaissance(new Date());
                p1.setMalade(true);
                //
                Stream.of("Samira", "Ihssan", "Radouan", "Ayoub", "Mustapha", "Hayat")
                        .forEach(name -> {
                            Patient patient = new Patient();
                            patient.setNom(name);
                            patient.setDateNaissance(new Date());
                            patient.setMalade(false);
                            hospitalService.savePatient(patient);
                        });

                Patient mohamed = patientRepository.findByNom("Mohamed");
                Patient patient_0 = patientRepository.findById(1L).orElse(null);
                patientRepository.save(patient_0);
                hospitalService.saveMedecin(medecin_0);

                RendezVous rendezVous_0 = new RendezVous();
                rendezVous_0.setDateRDV(new Date());
                rendezVous_0.setStatut(Statut.DONE);
                rendezVous_0.setMedecin(medecin_0);
                rendezVous_0.setPatient(patient_0);
//            rendezVous_0.setConsultation(consultation_0);
                hospitalService.saveRendezVous(rendezVous_0);

                RendezVous rendezVous_1 = rendezVousRepository.findAll().get(0);
                hospitalService.saveRendezVous(rendezVous_1);

                Consultation consultation_0 = new Consultation();
                consultation_0.setDateConsultation(rendezVous_0.getDateRDV());
                consultation_0.setRapportConsultation("rapport");
                hospitalService.saveConsultation(consultation_0);
            }
        };


    }
}