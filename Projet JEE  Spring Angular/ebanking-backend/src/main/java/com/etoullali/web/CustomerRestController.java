package com.etoullali.web;

import com.etoullali.dtos.CustomerDTO;
import com.etoullali.entities.Customer;
import com.etoullali.exceptions.CustomerNotFoundException;
import com.etoullali.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Web Service RestFull ou API**/
@RestController
@AllArgsConstructor
@CrossOrigin("*") // autorise tous les domaines
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomer(@RequestParam(defaultValue = "") String keyword){
        return bankAccountService.searchCustumer(keyword); //"%"+keyword+"%"
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        return bankAccountService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){ //@RequestBody : cad les données de customerDTO on va récupérer à partir de format JSON
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        CustomerDTO newCustomerDTO = bankAccountService.updateCustomer(customerDTO);
        return newCustomerDTO;
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        bankAccountService.deleteCustomer(id);
    }
}
