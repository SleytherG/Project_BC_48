package com.nttdata.emeal.msvc.debt.controller;

import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.service.DebtService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DebtController.DEBTS)
public class DebtController {

  public static final String DEBTS = "/debts";
  public static final String DEBT_ID = "/{debtId}";
  public static final String PRODUCT_ID = "/{productId}";
  public static final String CLIENT_ID = "/{clientId}";
  public static final String GET_ALL_DEBTS_BY_CLIENT_ID = "/getAllDebtsByClientId";
  public static final String GET_ALL_DEBTS_BY_PRODUCT_ID = "/getAllDebtsByProductId";


  @Autowired
  private DebtService debtService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Debt> retrieveAllDebts() {
    return debtService.retrieveAllDebts();
  }

  @GetMapping(value = GET_ALL_DEBTS_BY_CLIENT_ID  + CLIENT_ID,produces = {"application/json"})
  public Flowable<Debt> getAllDebtsByClientId(@PathVariable String clientId) {
    return debtService.getAllDebtsByClientId(clientId);
  }

  @GetMapping(DEBT_ID)
  public Maybe<Debt> retrieveADebt(@PathVariable String debtId) {
    return debtService.retrieveADebt(debtId);
  }

  @GetMapping(GET_ALL_DEBTS_BY_PRODUCT_ID + PRODUCT_ID)
  public Flowable<Debt> getAllDebtsByProductId(@PathVariable String productId) {
    return debtService.getAllDebtsByProductId(productId);
  }

  @PostMapping(produces = {"application/json"})
  public Single<Debt> saveDebt(@RequestBody Debt debt) {
    return debtService.saveDebt(debt);
  }

  @PutMapping(DEBT_ID)
  public Single<Debt> updateDebt(@PathVariable String debtId, @RequestBody Debt debt) {
    return debtService.updateDebt(debtId, debt);
  }

  @DeleteMapping(DEBT_ID)
  public Completable deleteDebt(@PathVariable String debtId) {
    return debtService.deleteDebt(debtId);
  }

}
