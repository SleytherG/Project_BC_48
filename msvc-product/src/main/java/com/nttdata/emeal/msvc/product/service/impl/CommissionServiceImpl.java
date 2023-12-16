package com.nttdata.emeal.msvc.product.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.emeal.msvc.product.model.Client;
import com.nttdata.emeal.msvc.product.model.Commission;
import com.nttdata.emeal.msvc.product.service.CommissionService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.adapter.rxjava.RxJava3Adapter;

import java.util.Map;


@Service
public class CommissionServiceImpl implements CommissionService {

  private final String COMMISSION_ID = "/{commissionId}";
  private final String PRODUCT_ID = "/{productId}";
  public static final String GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID = "/getAllCommissionsCurrentMonthByProductId";

  private final WebClient webClient;

  public CommissionServiceImpl() {
    this.webClient = WebClient.create("http://localhost:9190/commissions");
  }

  @Override
  public Single<Commission> saveCommission(Commission commission) {
    Map<String, Object> commissionMap = convertToMap(commission);
    return RxJava3Adapter.monoToSingle(
      webClient
        .post()
        .uri("")
        .body(Single.just(commissionMap), Map.class)
        .retrieve()
        .bodyToMono(Commission.class)
    );
  }

  @Override
  public Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId) {
    return RxJava3Adapter.fluxToFlowable(
      webClient
        .get()
        .uri(GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID + PRODUCT_ID, productId)
        .retrieve()
        .bodyToFlux(Commission.class)
    );
  }


  public static Map<String, Object> convertToMap(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
  }

}
