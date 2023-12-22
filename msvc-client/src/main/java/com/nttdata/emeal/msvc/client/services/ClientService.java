package com.nttdata.emeal.msvc.client.services;

import com.nttdata.emeal.msvc.client.dto.ClientDTO;
import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface ClientService {

  Flowable<Client> retrieveAllClients();
  Maybe<Client> retrieveAClient(String clientId);
  Single<Client> saveClient(Client client);
  Single<Client> updateClient(String clientId, Client client);
  Completable deleteClient(String clientId);

  Single<PersonalClient> savePersonalClient(PersonalClient personalClient);

  Single<EnterpriseClient> saveEnterpriseClient(EnterpriseClient enterpriseClient);

  Single<PersonalClient> updatePersonalClient(String clientId, PersonalClient personalClient);

  Single<EnterpriseClient> updateEnterpriseClient(String clientId, EnterpriseClient enterpriseClient);
}
