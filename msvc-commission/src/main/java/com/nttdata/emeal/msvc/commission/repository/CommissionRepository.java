package com.nttdata.emeal.msvc.commission.repository;

import com.nttdata.emeal.msvc.commission.model.Commission;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface CommissionRepository extends RxJava3CrudRepository<Commission, String> {

  Flowable<Commission> getCommissionsByProductId(String productId);
}
