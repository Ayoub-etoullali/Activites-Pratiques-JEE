package ma.enset.patient.web;

import lombok.AllArgsConstructor;
import ma.enset.patient.entities.Patient;
import ma.enset.patient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//consultation dans le web
@Controller
//=> 1éme Solution : vs @RestController qui comprent que la retourn doit l'affecter à dans le body de la réponse (la vue)
@AllArgsConstructor
public class PatientController {
    public PatientRepository patientRepository;

    @GetMapping(path = "/index")
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

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) { //par défaut @RequestParam (conserve name)
        patientRepository.deleteById(id);
        return "redirect:/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody // 2éme Solution : List<Patient> serialiser dans le body de la réponse
    public List<Patient> ListPatients(){
        return patientRepository.findAll();
    }
}
