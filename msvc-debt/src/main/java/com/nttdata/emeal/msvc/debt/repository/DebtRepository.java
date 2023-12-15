package com.nttdata.emeal.msvc.debt.repository;

import com.nttdata.emeal.msvc.debt.model.Debt;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface DebtRepository extends RxJava3CrudRepository<Debt, String> {

  Flowable<Debt> getDebtsByIdClient(String clientId);

  Flowable<Debt> getDebtsByIdProduct(String productId);
}
