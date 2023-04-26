package com.etoullali.test;

import com.etoullali.repositories.AbonnementRepository;
import com.etoullali.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplication {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AbonnementRepository abonnementRepository;

    @Test
    void contextLoads() {

    }
}