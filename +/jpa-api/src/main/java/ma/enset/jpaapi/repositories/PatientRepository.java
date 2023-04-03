package ma.enset.jpaapi.repositories;

import jdk.jfr.Percentage;
import lombok.Data;
import ma.enset.jpaapi.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByMalade(boolean m);       //find = Select [SQL]
    Page<Patient> findByMalade(boolean m, Pageable pageable);       //Pageable, c'est un interface de Spring data, il est essensiel si on return Page
    List<Patient> findByMaladeAndScoreLessThan(boolean m,int s);
    List<Patient> findByMaladeIsTrueAndScoreLessThan(int s);

    // List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1,Date d2,String nom);
    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :nom")
    List<Patient> ChercherPatient(@Param("x") Date d1,@Param("y") Date d2,@Param("nom") String nom);
}
