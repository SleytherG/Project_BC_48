package com.nttdata.emeal.msvc.client.controller;


import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import com.nttdata.emeal.msvc.client.services.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClientController.CLIENTS)
public class ClientController {

  public static final String CLIENTS = "/clients";
  public static final String CLIENT_ID = "/{clientId}";
  public static final String CREATE_PERSONAL_CLIENT = "/createPersonaClient";
  public static final String CREATE_ENTERPRISE_CLIENT = "/createEnterpriseClient";
  public static final String UPDATE_PERSONAL_CLIENT = "/updatePersonalClient";
  public static final String UPDATE_ENTERPRISE_CLIENT = "/updateEnterpriseClient";

  @Autowired
  private ClientService clientService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Client> retrieveAllClients() {
    return clientService.retrieveAllClients();
  }

  @GetMapping(CLIENT_ID)
  public Maybe<Client> retrieveAClient(@PathVariable String clientId) {
    return clientService.retrieveAClient(clientId);
  }

  @PostMapping(produces = {"application/json"})
  public Single<Client> saveClient(@RequestBody Client client) {
    return clientService.saveClient(client);
  }

  @PostMapping(value = CREATE_PERSONAL_CLIENT,produces = {"application/json"})
  public Single<PersonalClient> savePersonalClient(@RequestBody PersonalClient personalClient) {
    return clientService.savePersonalClient(personalClient);
  }

  @PostMapping(value =  CREATE_ENTERPRISE_CLIENT, produces = {"application/json"})
  public Single<EnterpriseClient> saveEnterpriseClient(@RequestBody EnterpriseClient enterpriseClient) {
    return clientService.saveEnterpriseClient(enterpriseClient);
  }

  @PutMapping(CLIENT_ID)
  public Single<Client> updateClient(@PathVariable String clientId, @RequestBody Client client) {
    return clientService.updateClient(clientId, client);
  }

  @PutMapping(UPDATE_PERSONAL_CLIENT + CLIENT_ID)
  public Single<PersonalClient> updatePersonalClient(@PathVariable String clientId, @RequestBody PersonalClient personalClient) {
    return clientService.updatePersonalClient(clientId, personalClient);
  }

  @PutMapping(UPDATE_ENTERPRISE_CLIENT + CLIENT_ID)
  public Single<EnterpriseClient> updateEnterpriseClient(@PathVariable String clientId, @RequestBody EnterpriseClient enterpriseClient) {
    return clientService.updateEnterpriseClient(clientId, enterpriseClient);
  }

  @DeleteMapping(CLIENT_ID)
  public Completable deleteClient(@PathVariable String clientId) {
    return clientService.deleteClient(clientId);
  }

}
