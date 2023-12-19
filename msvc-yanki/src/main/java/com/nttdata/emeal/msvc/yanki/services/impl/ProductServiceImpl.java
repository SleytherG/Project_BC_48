package com.nttdata.emeal.msvc.yanki.services.impl;

import com.nttdata.emeal.msvc.yanki.model.BankAccount;
import com.nttdata.emeal.msvc.yanki.services.ProductService;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
public class ProductServiceImpl implements ProductService {

  private final String CLIENT_ID = "/{clientId}";


  private final WebClient webClient;

  public ProductServiceImpl() {
    this.webClient = WebClient.create("http://localhost:9190/products");
  }

  @Override
  public Flowable<BankAccount> getProductsByClientId(String clientId) {
    return RxJava3Adapter.monoToFlowable(
      webClient
        .get()
        .uri(CLIENT_ID, clientId)
        .retrieve()
        .bodyToMono(BankAccount.class)
    );
  }

}
