package com.etoullali.web;

import com.etoullali.entities.Abonnement;
import com.etoullali.entities.Client;
import com.etoullali.repositories.AbonnementRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.etoullali.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//consultation dans le web
@Controller
//=> 1éme Solution : vs @RestController qui comprent que la retourn doit l'affecter à dans le body de la réponse (la vue)
@AllArgsConstructor
public class ClientController {
    public ClientRepository clientRepository;
    public AbonnementRepository abonnementRepository;

    @GetMapping("/")
    public String home() {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/client/index";

        }

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {
            return "AbonnementClient_CLIENT";

        }

        return null;
    }

    //Liste des clients + Abonnements : ADMIN & CLIENT
    @GetMapping(path = "/client/index")
    public String clients(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            Page<Client> clients = clientRepository.findByNomContains(keyword, PageRequest.of(page, size));
            model.addAttribute("listClients", clients.getContent());
            model.addAttribute("pages", new int[clients.getTotalPages()]);
            model.addAttribute("currentPage", page);
            model.addAttribute("keyword", keyword);

            return "clients";
        }

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(
                authority -> authority.getAuthority().equals("ROLE_CLIENT"))) {

            return "AbonnementClient_CLIENT";
        }

        return null;
    }

    //formulaire client => Ajouter client
    @GetMapping("/admin/formClients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formClients(Model model) {
        model.addAttribute("client", new Client()); //des valeurs par défaut
        return "formClients";
    }

    //Ajouter client
    @PostMapping(path = "/admin/saveClient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveClient(Model model, @Valid Client client, BindingResult bindingResult, //BindingResult : collection des erreurs
                             @RequestParam(defaultValue = "") String keyword,
                             @RequestParam(defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formClients";
        clientRepository.save(client);
        return "redirect:/client/index?page" + page + "&keyword" + keyword;
    }

    //Editer un client
    @GetMapping(path = "/admin/editClient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editClient(Model model, Long id, String keyword, int page) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) throw new RuntimeException("Client not found");
        model.addAttribute("client", client);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editClient";
    }

    //Supprimer un client
    @GetMapping("/admin/deleteClient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteClient(Long id, int page, String keyword) { //par défaut @RequestParam (conserve name)
        clientRepository.deleteById(id);
        return "redirect:/client/index?page" + page + "&keyword=" + keyword;
    }

    //Abonnement de cLient
    @GetMapping("/admin/AbonnementClient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AbonnementClient(Model model, Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        Collection<Abonnement> abonnement = abonnementRepository.findByClient(client);

        model.addAttribute("client", client);
        model.addAttribute("abonnement", abonnement);
        return "AbonnementClient_ADMIN";
    }

    //Recharger Solde
    /*@PostMapping("/client/rechargerSolde")
    public String rechargerSolde(@PathVariable Long abonnementId, @RequestParam("montant") double montant) {
        abonnementService.rechargerSolde(abonnementId, montant);
        return "AbonnementClient_ADMIN";
    }*/

    //Supprimer un abonnement
    @GetMapping("/admin/deleteAbonnement")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAbonnement(Long id) { //par défaut @RequestParam (conserve name)
        abonnementRepository.deleteById(id);
        return "redirect:/admin/AbonnementClient?id="+id;
    }

}

