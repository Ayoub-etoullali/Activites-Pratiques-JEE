package com.etoullali.web;

import com.etoullali.dtos.ClientDTO;
import com.etoullali.dtos.VolDTO;
import com.etoullali.services.VolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class VolController {
    private VolService volService;

    @GetMapping("/vols/all")
    public List<VolDTO> Vols(){
        return volService.getAllVols();
    }

    @GetMapping("/vols/{id}")
    public VolDTO getVol(@PathVariable(name = "id") Long id){
        return volService.getVolById(id);
    }

    @GetMapping("/clients/all")
    public List<ClientDTO> Clients(){
        return volService.getAllClients();
    }

    @GetMapping("/vols/search")
    public List<VolDTO> searchCustomer(@RequestParam(defaultValue = "") String keyword){
        return volService.searchVol(keyword); //"%"+keyword+"%"
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable(name = "id") Long id){
        return volService.getClientById(id);
    }
}
