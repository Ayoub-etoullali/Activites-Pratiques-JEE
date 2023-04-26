package com.etoullali.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.etoullali.enums.TypeAbonnement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.etoullali.repositories.ClientRepository;
import com.etoullali.entities.Client;
import com.etoullali.entities.Abonnement;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testFindByNom() {
        // given
        Client client1 = new Client(1L,"clt1","e1","u1",null);
        clientRepository.save(client1);

        // when
        List<Client> clients = clientRepository.findByNom("nom1");

        // then
        assertThat(clients).isNotNull();
        assertThat(clients.size()).isEqualTo(1);
        assertThat(clients.get(0).getId()).isEqualTo(client1.getId());
    }

    @Test
    public void testFindByUsername() {
        // given
        Client client2 = new Client(2L,"clt2","e2","u2",null);
        clientRepository.save(client2);

        // when
        Optional<Client> optionalClient = clientRepository.findByUsername("username2");

        // then
        assertThat(optionalClient).isPresent();
        assertThat(optionalClient.get().getId()).isEqualTo(client2.getId());
    }

    @Test
    public void testCascadePersist() {
        // given
        Client client3 = new Client(3L,"clt3","e3","u1",null);
        Abonnement abonnement1 = new Abonnement(1L, new Date(), TypeAbonnement.GSM, 100.0, 50.0,client3);
        Abonnement abonnement2 = new Abonnement(2L, new Date(), TypeAbonnement.INTERNET, 200.0, 100.0,client3);
        client3.getAbonnements().add(abonnement1);
        client3.getAbonnements().add(abonnement2);

        // when
        clientRepository.save(client3);

        // then
        assertThat(client3.getId()).isNotNull();
        assertThat(abonnement1.getId()).isNotNull();
        assertThat(abonnement2.getId()).isNotNull();
        assertThat(clientRepository.findById(client3.getId())).isPresent();
    }

}