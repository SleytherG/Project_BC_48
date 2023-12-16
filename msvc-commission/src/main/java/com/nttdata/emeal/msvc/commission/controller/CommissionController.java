package com.nttdata.emeal.msvc.commission.controller;

import com.nttdata.emeal.msvc.commission.model.Commission;
import com.nttdata.emeal.msvc.commission.services.CommissionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommissionController.COMMISSIONS)
public class CommissionController {

  public static final String COMMISSIONS = "/commissions";
  public static final String COMMISSION_ID = "/{commissionId}";
  public static final String PRODUCT_ID = "/{productId}";
  public static final String GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID = "/getAllCommissionsCurrentMonthByProductId";

  @Autowired
  private CommissionService commissionService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Commission> retrieveAllCommissions() {
    return commissionService.retrieveAllCommissions();
  }

  @GetMapping(COMMISSION_ID)
  public Maybe<Commission> retrieveACommission(@PathVariable String commissionId) {
    return commissionService.retrieveACommission(commissionId);
  }

  @PostMapping(produces = {"application/json"})
  public Single<Commission> saveCommission(@RequestBody Commission commission) {
    return commissionService.saveCommission(commission);
  }

  @DeleteMapping(COMMISSION_ID)
  public Completable deleteCommission(@PathVariable String commissionId) {
    return commissionService.deleteCommission(commissionId);
  }

  @GetMapping(GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID + PRODUCT_ID)
  public Flowable<Commission> getAllCommissionsCurrentMonthByProductId(@PathVariable String productId) {
    return commissionService.getAllCommissionsCurrentMonthByProductId(productId);
  }
}
