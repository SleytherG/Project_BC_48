package com.nttdata.emeal.msvc.product.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.emeal.msvc.product.model.Debt;
import com.nttdata.emeal.msvc.product.service.DebtService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

import java.util.Map;

@Service
public class DebtServiceImpl implements DebtService {

  private final WebClient webClient;

  public final String CLIENT_ID = "/{clientId}";
  public final String PRODUCT_ID = "/{productId}";
  public final String DEBT_ID = "/{debtId}";

  public final String GET_ALL_DEBTS_BY_CLIENT_ID = "/getAllDebtsByClientId";
  public final String GET_ALL_DEBTS_BY_PRODUCT_ID = "/getAllDebtsByProductId";

  public DebtServiceImpl() {
    this.webClient = WebClient.create("http://localhost:9190/debts");
  }

  @Override
  public Flowable<Debt> getAllDebtsByClientId(String clientId) {
    return RxJava3Adapter.fluxToFlowable(
      webClient
        .get()
        .uri(GET_ALL_DEBTS_BY_CLIENT_ID + CLIENT_ID,  clientId)
        .retrieve()
        .bodyToFlux(Debt.class)
    );
  }

  @Override
  public Flowable<Debt> getAllDebtsByProductId(String productId) {
    return RxJava3Adapter.fluxToFlowable(
      webClient
        .get()
        .uri(GET_ALL_DEBTS_BY_PRODUCT_ID + PRODUCT_ID,  productId)
        .retrieve()
        .bodyToFlux(Debt.class)
    );
  }

  @Override
  public Completable updateDebt(String debtId, Debt debt) {
    Map<String, Object> debtMap = convertToMap(debt);
    return RxJava3Adapter.monoToCompletable(
      webClient
        .post()
        .uri( DEBT_ID,  debtId)
        .body(Single.just(debtMap), Map.class)
        .retrieve()
        .bodyToMono(Debt.class)
    );
  }

  public static Map<String, Object> convertToMap(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
  }
}
