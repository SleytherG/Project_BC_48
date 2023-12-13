package com.nttdata.emeal.msvc.client.repository;

import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ClientRepository extends RxJava3CrudRepository<Client, String> {

  Single<PersonalClient> save(PersonalClient personalClient);
  Single<EnterpriseClient> save(EnterpriseClient enterpriseClient);
}
