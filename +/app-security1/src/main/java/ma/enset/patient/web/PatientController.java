package ma.enset.patient.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.patient.entities.Patient;
import ma.enset.patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//consultation dans le web
@Controller
//=> 1éme Solution : vs @RestController qui comprent que la retourn doit l'affecter à dans le body de la réponse (la vue)
@AllArgsConstructor
public class PatientController {
    public PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    public String patient(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContaining(keyword, PageRequest.of(page, size));
        model.addAttribute("listePatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("pageCurrent", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(Long id, int page, String keyword) { //par défaut @RequestParam (conserve name)
        patientRepository.deleteById(id);
        return "redirect:/user/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("patients")
    @ResponseBody // 2éme Solution : List<Patient> serialiser dans le body de la réponse
    public List<Patient> ListPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient",new Patient()); //des valeurs par défaut
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult, //BindingResult : collection des erreurs
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0")int page) {
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page"+page+"&keyword"+keyword;
    }

    @GetMapping(path = "/admin/editPatient")
    public String editPatient(Model model,Long id,String keyword,int page) {
        Patient patient=patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
}
