package com.nttdata.emeal.msvc.debt.service;

import com.nttdata.emeal.msvc.debt.model.Debt;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface DebtService {
  Flowable<Debt> retrieveAllDebts();

  Maybe<Debt> retrieveADebt(String debtId);

  Single<Debt> saveDebt(Debt debt);

  Single<Debt> updateDebt(String debtId, Debt debt);

  Completable deleteDebt(String debtId);
}
