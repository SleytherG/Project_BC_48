package com.nttdata.emeal.msvc.client.controller;

import com.nttdata.emeal.msvc.client.mapper.ClientMapper;
import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import com.nttdata.emeal.msvc.client.services.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ClientControllerTest {

  private Client client;
  private PersonalClient personalClient;
  private EnterpriseClient enterpriseClient;
  private Client clientUpdated;
  private PersonalClient personalClientUpdated;
  private EnterpriseClient enterpriseClientUpdated;

  @Mock
  private ClientService clientService;

  @Mock
  private ClientMapper clientMapper;

  @InjectMocks
  private ClientController clientController;

  @BeforeEach
  void setUp() {
    this.client = new Client();
    this.client.setId("1");
    this.client.setDocumentNumber("100");
    this.client.setDocumentType("DNI");
    this.client.setPhone("1234");
    this.client.setEmail("test@example.com");

    this.clientUpdated = new Client();
    this.clientUpdated.setId("10");
    this.clientUpdated.setDocumentNumber("1000");
    this.clientUpdated.setDocumentType("DNI");
    this.clientUpdated.setPhone("12340");
    this.clientUpdated.setEmail("test0@example.com");

    this.personalClient = new PersonalClient();
    this.personalClient.setId("2");
    this.personalClient.setDocumentNumber("101");
    this.personalClient.setDocumentType("DNI");
    this.personalClient.setPhone("12345");
    this.personalClient.setEmail("personal@example.com");
    this.personalClient.setNames("John Doe");
    this.personalClient.setSurnames("Mr. Franz");

    this.personalClientUpdated = new PersonalClient();
    this.personalClientUpdated.setId("20");
    this.personalClientUpdated.setDocumentNumber("1010");
    this.personalClientUpdated.setDocumentType("DNI");
    this.personalClientUpdated.setPhone("123450");
    this.personalClientUpdated.setEmail("personalUpdated@example.com");
    this.personalClientUpdated.setNames("Johnny Doe");
    this.personalClientUpdated.setSurnames("Mr. Francis");

    this.enterpriseClient = new EnterpriseClient();
    this.enterpriseClient.setId("3");
    this.enterpriseClient.setDocumentNumber("102");
    this.enterpriseClient.setDocumentType("RUC");
    this.enterpriseClient.setPhone("123456");
    this.enterpriseClient.setEmail("enterprise@example.com");
    this.enterpriseClient.setBusinessName("BCP");

    this.enterpriseClientUpdated = new EnterpriseClient();
    this.enterpriseClientUpdated.setId("30");
    this.enterpriseClientUpdated.setDocumentNumber("1020");
    this.enterpriseClientUpdated.setDocumentType("RUC");
    this.enterpriseClientUpdated.setPhone("1234560");
    this.enterpriseClientUpdated.setEmail("enterpriseUpdated@example.com");
    this.enterpriseClientUpdated.setBusinessName("BCP Updated");

  }

  @Test
  @Order(2)
  @Disabled
  void testRetrieveAllClients() {
    // Arrange
    Mockito.when(clientService.retrieveAllClients()).thenReturn(Flowable.just(client, personalClient, enterpriseClient));
//
//    // Act
    TestSubscriber<Client> testSubscriber = clientController.retrieveAllClients().test();


    // Assert
    Mockito.verify(clientService).retrieveAllClients();
//    testSubscriber.assertValues(
//       client -> {
//        assertEquals("1", client.getId());
//        assertEquals("100", client.getDocumentNumber());
//        assertEquals("DNI", client.getDocumentType());
//        assertEquals("1234", client.getPhone());
//        assertEquals("test@example.com", client.getEmail());
//      },
//      personalClient -> {
//      assertNotNull(personalClient);
//      assertEquals("2", personalClient.getId());
//      assertEquals("101", personalClient.getDocumentNumber());
//      assertEquals("DNI", personalClient.getDocumentType());
//      assertEquals("12345", personalClient.getPhone());
//      assertEquals("personal@example.com", personalClient.getEmail());
//      assertEquals("John Doe", ((PersonalClient) personalClient).getNames());
//      assertEquals("Mr. Franz", ((PersonalClient) personalClient).getSurnames());
//      },
//      enterpriseClient -> {
//      assertNotNull(enterpriseClient);
//      assertEquals("3", enterpriseClient.getId());
//      assertEquals("102", enterpriseClient.getDocumentNumber());
//      assertEquals("RUC", enterpriseClient.getDocumentType());
//      assertEquals("123456", enterpriseClient.getPhone());
//      assertEquals("enterprise@example.com", enterpriseClient.getEmail());
//      assertEquals("BCP", ((EnterpriseClient) enterpriseClient).getBusinessName());
//      }
//    );
//    testSubscriber.assertValue(personalClient -> {
//      assertNotNull(personalClient);
//      assertEquals("2", personalClient.getId());
//      assertEquals("101", personalClient.getDocumentNumber());
//      assertEquals("DNI", personalClient.getDocumentType());
//      assertEquals("12345", personalClient.getPhone());
//      assertEquals("personal@example.com", personalClient.getEmail());
//      assertEquals("John Doe", ((PersonalClient) personalClient).getNames());
//      assertEquals("Mr. Franz", ((PersonalClient) personalClient).getSurnames());
//      return true;
//    });
//    testSubscriber.assertValue(enterpriseClient -> {
//      assertNotNull(enterpriseClient);
//      assertEquals("3", enterpriseClient.getId());
//      assertEquals("102", enterpriseClient.getDocumentNumber());
//      assertEquals("RUC", enterpriseClient.getDocumentType());
//      assertEquals("123456", enterpriseClient.getPhone());
//      assertEquals("enterprise@example.com", enterpriseClient.getEmail());
//      assertEquals("BCP", ((EnterpriseClient) enterpriseClient).getBusinessName());
//      return true;
//    });
    testSubscriber.assertComplete().assertNoErrors();
  }

  @Test
  @Order(3)
  void testRetrieveAClient() {

    // Arrange
    Mockito.when(clientService.retrieveAClient(client.getId())).thenReturn(Maybe.just(client));

    // Act
    Client clientFounded = clientController.retrieveAClient(client.getId()).blockingGet();
    TestObserver<Client> testObserver = clientController.retrieveAClient(client.getId()).test();

    // Assert
    assertNotNull(clientFounded);
    testObserver.assertValue(clientF -> {
      assertNotNull(clientF);
      assertEquals("1", clientF.getId());
      assertEquals("100", clientF.getDocumentNumber());
      assertEquals("DNI", clientF.getDocumentType());
      assertEquals("1234", clientF.getPhone());
      assertEquals("test@example.com", clientF.getEmail());
      return true;
    });
    testObserver.assertComplete();
    testObserver.assertNoErrors();

  }

  @Test
  @Order(1)
  void testSaveClient() {
    // Arrange
    Mockito.when(clientService.saveClient(client)).thenReturn(Single.just(client));
    Mockito.when(clientService.saveClient(personalClient)).thenReturn(Single.just(personalClient));
    Mockito.when(clientService.saveClient(enterpriseClient)).thenReturn(Single.just(enterpriseClient));

    // Act
    TestObserver<Client> testObserverC = clientController.saveClient(client).test();
    TestObserver<Client> testObserverP = clientController.saveClient(personalClient).test();
    TestObserver<Client> testObserverE = clientController.saveClient(enterpriseClient).test();

    // Assert
    Mockito.verify(clientService).saveClient(client);
    Mockito.verify(clientService).saveClient(personalClient);
    Mockito.verify(clientService).saveClient(enterpriseClient);

    testObserverC.assertComplete().assertNoErrors();
    testObserverP.assertComplete().assertNoErrors();
    testObserverE.assertComplete().assertNoErrors();
    testObserverC.assertValue(clientSaved -> {
      assertNotNull(clientSaved);
      assertEquals("1", clientSaved.getId());
      assertEquals("100", clientSaved.getDocumentNumber());
      assertEquals("DNI", clientSaved.getDocumentType());
      assertEquals("1234", clientSaved.getPhone());
      assertEquals("test@example.com", clientSaved.getEmail());
      return true;
    });

    testObserverP.assertValue(personalClientSaved -> {
      assertNotNull(personalClientSaved);
      assertEquals("2", personalClientSaved.getId());
      assertEquals("101", personalClientSaved.getDocumentNumber());
      assertEquals("DNI", personalClientSaved.getDocumentType());
      assertEquals("12345", personalClientSaved.getPhone());
      assertEquals("personal@example.com", personalClientSaved.getEmail());
      assertEquals("John Doe", ((PersonalClient) personalClientSaved).getNames());
      assertEquals("Mr. Franz", ((PersonalClient) personalClientSaved).getSurnames());
      return true;
    });

    testObserverE.assertValue(enterpriseClientSaved -> {
      assertNotNull(enterpriseClientSaved);
      assertEquals("3", enterpriseClientSaved.getId());
      assertEquals("102", enterpriseClientSaved.getDocumentNumber());
      assertEquals("RUC", enterpriseClientSaved.getDocumentType());
      assertEquals("123456", enterpriseClientSaved.getPhone());
      assertEquals("enterprise@example.com", enterpriseClientSaved.getEmail());
      assertEquals("BCP", ((EnterpriseClient) enterpriseClientSaved).getBusinessName());
      return true;
    });

  }

  @Test
  @Order(4)
  void testUpdateClient() {
    // Arrange
    Mockito.when(clientService.updateClient(client.getId(), client)).thenReturn(Single.just(clientUpdated));
    Mockito.when(clientService.updateClient(personalClient.getId(), personalClient)).thenReturn(Single.just(personalClientUpdated));
    Mockito.when(clientService.updateClient(enterpriseClient.getId(), enterpriseClient)).thenReturn(Single.just(enterpriseClientUpdated));

    // Act
    TestObserver<Client> testObserverC = clientController.updateClient(client.getId(), client).test();
    TestObserver<Client> testObserverP = clientController.updateClient(personalClient.getId(), personalClient).test();
    TestObserver<Client> testObserverE = clientController.updateClient(enterpriseClient.getId(), enterpriseClient).test();



    // Assert

    Mockito.verify(clientService).updateClient(client.getId(), client);
    Mockito.verify(clientService).updateClient(personalClient.getId(), personalClient);
    Mockito.verify(clientService).updateClient(enterpriseClient.getId(), enterpriseClient);
    testObserverC.assertComplete().assertNoErrors();
    testObserverP.assertComplete().assertNoErrors();
    testObserverE.assertComplete().assertNoErrors();

    testObserverC.assertValue(clientUpdated -> {
      assertNotNull(clientUpdated);
      assertEquals("10", clientUpdated.getId());
      assertEquals("1000", clientUpdated.getDocumentNumber());
      assertEquals("DNI", clientUpdated.getDocumentType());
      assertEquals("12340", clientUpdated.getPhone());
      assertEquals("test0@example.com", clientUpdated.getEmail());
      return true;
    });

    testObserverP.assertValue(personalClientUpdated -> {
      assertNotNull(personalClientUpdated);
      assertEquals("20", personalClientUpdated.getId());
      assertEquals("1010", personalClientUpdated.getDocumentNumber());
      assertEquals("DNI", personalClientUpdated.getDocumentType());
      assertEquals("123450", personalClientUpdated.getPhone());
      assertEquals("personalUpdated@example.com", personalClientUpdated.getEmail());
      assertEquals("Johnny Doe", ((PersonalClient) personalClientUpdated).getNames());
      assertEquals("Mr. Francis", ((PersonalClient) personalClientUpdated).getSurnames());
      return true;
    });

    testObserverE.assertValue(enterpriseClientUpdated -> {
      assertNotNull(enterpriseClientUpdated);
      assertEquals("30", enterpriseClientUpdated.getId());
      assertEquals("1020", enterpriseClientUpdated.getDocumentNumber());
      assertEquals("RUC", enterpriseClientUpdated.getDocumentType());
      assertEquals("1234560", enterpriseClientUpdated.getPhone());
      assertEquals("enterpriseUpdated@example.com", enterpriseClientUpdated.getEmail());
      assertEquals("BCP Updated", ((EnterpriseClient) enterpriseClientUpdated).getBusinessName());
      return true;
    });

  }

  @Test
  @Order(5)
  void testDeleteClient() {
    // Arrange
    Mockito.when(clientService.deleteClient(client.getId())).thenReturn(Completable.complete());
    Mockito.when(clientService.deleteClient(personalClient.getId())).thenReturn(Completable.complete());
    Mockito.when(clientService.deleteClient(enterpriseClient.getId())).thenReturn(Completable.complete());

    // Act
    Completable resultC = clientController.deleteClient(client.getId()).andThen(Completable.complete());
    Completable resultP = clientController.deleteClient(personalClient.getId()).andThen(Completable.complete());
    Completable resultE = clientController.deleteClient(enterpriseClient.getId()).andThen(Completable.complete());
    TestObserver<Void> testObserverC = resultC.test();
    TestObserver<Void> testObserverP = resultP.test();
    TestObserver<Void> testObserverE = resultE.test();

    // Assert
    testObserverC.assertComplete().assertNoErrors();
    testObserverP.assertComplete().assertNoErrors();
    testObserverE.assertComplete().assertNoErrors();
    Mockito.verify(clientService).deleteClient(client.getId());
    Mockito.verify(clientService).deleteClient(personalClient.getId());
    Mockito.verify(clientService).deleteClient(enterpriseClient.getId());

  }

  @Test
  void savePersonalClient() {
  }

  @Test
  void saveEnterpriseClient() {
  }
}