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

  @Autowired
  private DebtService debtService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Debt> retrieveAllDebts() {
    return debtService.retrieveAllDebts();
  }

  @GetMapping(DEBT_ID)
  public Maybe<Debt> retrieveADebt(@PathVariable String debtId) {
    return debtService.retrieveADebt(debtId);
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
