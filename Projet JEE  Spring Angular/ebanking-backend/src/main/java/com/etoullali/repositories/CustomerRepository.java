package com.etoullali.repositories;

import com.etoullali.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContains(String keyword);
    //Autre méthode
    /*
    @Query("select c from Customer c where c.name like :kw")
    List<Customer> SearchCustomer(@Param(value = "kw") String keyword);
     */
}