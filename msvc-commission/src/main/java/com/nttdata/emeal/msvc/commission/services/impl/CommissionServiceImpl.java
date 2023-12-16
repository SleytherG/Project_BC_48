package com.nttdata.emeal.msvc.commission.services.impl;

import com.nttdata.emeal.msvc.commission.exceptions.CommissionNotFoundException;
import com.nttdata.emeal.msvc.commission.model.Commission;
import com.nttdata.emeal.msvc.commission.repository.CommissionRepository;
import com.nttdata.emeal.msvc.commission.services.CommissionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl implements CommissionService {


  @Autowired
  private CommissionRepository commissionRepository;

  @Override
  public Flowable<Commission> retrieveAllCommissions() {
    return commissionRepository.findAll();
  }

  @Override
  public Maybe<Commission> retrieveACommission(String commissionId) {
    return commissionRepository
      .findById(commissionId)
      .switchIfEmpty(Maybe.error(new CommissionNotFoundException("Commission not found with id: " + commissionId)));
  }

  @Override
  public Single<Commission> saveCommission(Commission commission) {
    return commissionRepository.save(commission);
  }

  @Override
  public Completable deleteCommission(String commissionId) {
    return commissionRepository
      .findById(commissionId)
      .flatMapCompletable(commission -> Completable.fromAction(() -> commissionRepository.deleteById(commissionId).subscribe()));
  }

  @Override
  public Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId) {
    return commissionRepository
      .getCommissionsByProductId(productId);
  }
}
