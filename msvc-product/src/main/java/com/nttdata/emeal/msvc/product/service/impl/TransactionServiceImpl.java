package com.nttdata.emeal.msvc.product.service.impl;

import com.nttdata.emeal.msvc.product.model.Debt;
import com.nttdata.emeal.msvc.product.model.Transaction;
import com.nttdata.emeal.msvc.product.service.TransactionService;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

@Service
public class TransactionServiceImpl implements TransactionService  {

  private final WebClient webClient;

  public final String GET_ALL_TRANSACTIONS_BY_PRODUCT_ID = "/getAllTransactionsByProductId";
  public final String GET_LAST_TEN_TRANSACTIONS_BY_PRODUCT_ID = "/getLastTenTransactionsByProductId";
  public final String PRODUCT_ID = "/{productId}";

  public TransactionServiceImpl() {
    this.webClient = WebClient.create("http://localhost:9190/transactions");
  }


  @Override
  public Flowable<Transaction> getAllTransactionsByProductId(String productId) {
    return RxJava3Adapter.fluxToFlowable(
      webClient
        .get()
        .uri(GET_ALL_TRANSACTIONS_BY_PRODUCT_ID + PRODUCT_ID,  productId)
        .retrieve()
        .bodyToFlux(Transaction.class)
    );
  }

  @Override
  public Flowable<Transaction> getLastTenTransactionsByProductId(String productId) {
    return RxJava3Adapter.fluxToFlowable(
      webClient
        .get()
        .uri(GET_LAST_TEN_TRANSACTIONS_BY_PRODUCT_ID + PRODUCT_ID,  productId)
        .retrieve()
        .bodyToFlux(Transaction.class)
    );
  }
}
