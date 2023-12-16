package com.nttdata.emeal.msvc.product.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.emeal.msvc.product.model.Client;
import com.nttdata.emeal.msvc.product.model.Debt;
import com.nttdata.emeal.msvc.product.service.ClientService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

  private final String CLIENT_ID = "/{clientId}";

  private final WebClient webClient;

  public ClientServiceImpl() {
    this.webClient = WebClient.create("http://localhost:9190/clients");
  }

  @Override
  public Maybe<Client> retrieveAClient(String clientId) {
    return RxJava3Adapter.monoToMaybe(
      webClient
        .get()
        .uri(CLIENT_ID, clientId)
        .retrieve()
        .bodyToMono(Client.class)
    );
  }

}
