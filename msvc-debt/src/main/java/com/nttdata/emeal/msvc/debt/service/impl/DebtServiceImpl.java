package com.nttdata.emeal.msvc.debt.service.impl;

import com.nttdata.emeal.msvc.debt.exceptions.DebtNotFoundException;
import com.nttdata.emeal.msvc.debt.mapper.DebtMapper;
import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.repository.DebtRepository;
import com.nttdata.emeal.msvc.debt.service.DebtService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DebtServiceImpl implements DebtService {

  @Autowired
  private DebtRepository debtRepository;

  @Autowired
  private DebtMapper debtMapper;

  @Override
  public Flowable<Debt> retrieveAllDebts() {
    return debtRepository.findAll();
  }

  @Override
  public Maybe<Debt> retrieveADebt(String debtId) {
    return debtRepository
      .findById(debtId)
      .switchIfEmpty(Maybe.error(new DebtNotFoundException("Debt not found with id: " + debtId)));
  }

  @Override
  public Single<Debt> saveDebt(Debt debt) {
    return debtRepository.save(debt);
  }

  @Override
  public Single<Debt> updateDebt(String debtId, Debt debt) {
    return debtRepository
      .findById(debtId)
      .switchIfEmpty(Single.error(new DebtNotFoundException("Debt not found with id: " + debtId)))
      .flatMap(debtFounded -> debtMapper.mapDebtAndSave(debtFounded, debt));
  }

  @Override
  public Completable deleteDebt(String debtId) {
    return debtRepository
      .findById(debtId)
      .switchIfEmpty(Maybe.error(new DebtNotFoundException("Debt not found with id: " + debtId)))
      .flatMapCompletable(debt -> Completable.fromAction(() -> debtRepository.deleteById(debtId).subscribe()))
      .doOnComplete(() -> log.debug("Debt deleted"));
  }

  @Override
  public Flowable<Debt> getAllDebtsByClientId(String clientId) {
    return debtRepository.getDebtsByIdClient(clientId);
  }

  @Override
  public Flowable<Debt> getAllDebtsByProductId(String productId) {
    return debtRepository.getDebtsByIdProduct(productId);
  }


}
