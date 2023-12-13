package com.nttdata.emeal.msvc.client.services.impl;

import com.nttdata.emeal.msvc.client.exceptions.ClientNotFoundException;
import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import com.nttdata.emeal.msvc.client.repository.ClientRepository;
import com.nttdata.emeal.msvc.client.services.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public Flowable<Client> retrieveAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Maybe<Client> retrieveAClient(String clientId) {
    return clientRepository.findById(clientId);
  }

  @Override
  public Single<Client> saveClient(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public Single<Client> updateClient(String clientId, Client client) {
    return clientRepository
      .findById(clientId)
      .switchIfEmpty(Single.error(new ClientNotFoundException("Client not found with id: " + clientId)))
      .flatMap(clientFounded -> {
        BeanUtils.copyProperties(client, clientFounded, "id");
        return clientRepository.save(clientFounded);
      });
  }

  @Override
  public Completable deleteClient(String clientId) {
    return clientRepository
      .findById(clientId)
      .switchIfEmpty(Maybe.error(new ClientNotFoundException("Client not found with id: " + clientId)))
      .flatMapCompletable(client -> Completable.fromAction(() -> clientRepository.deleteById(clientId).subscribe()))
      .doOnComplete(() -> log.info("Client deleted"));
  }

  @Override
  public Single<PersonalClient> savePersonalClient(PersonalClient personalClient) {
    return clientRepository.save(personalClient);
  }

  @Override
  public Single<EnterpriseClient> saveEnterpriseClient(EnterpriseClient enterpriseClient) {
    return clientRepository.save(enterpriseClient);
  }
}
