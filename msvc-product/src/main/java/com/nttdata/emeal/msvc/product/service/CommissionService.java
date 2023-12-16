package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.Commission;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface CommissionService {

  Single<Commission> saveCommission(Commission commission);

  Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId);
}
