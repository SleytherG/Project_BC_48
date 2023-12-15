package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.Debt;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface DebtService {

  Flowable<Debt> getAllDebtsByClientId(String clientId);

  Flowable<Debt> getAllDebtsByProductId(String productId);

  Completable updateDebt(String debtId, Debt debt);
}
