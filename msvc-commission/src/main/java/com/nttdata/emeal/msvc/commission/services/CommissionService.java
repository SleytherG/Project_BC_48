package com.nttdata.emeal.msvc.commission.services;

import com.nttdata.emeal.msvc.commission.model.Commission;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface CommissionService {
  Flowable<Commission> retrieveAllCommissions();

  Maybe<Commission> retrieveACommission(String commissionId);

  Single<Commission> saveCommission(Commission commission);

  Completable deleteCommission(String commissionId);

  Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId);
}
