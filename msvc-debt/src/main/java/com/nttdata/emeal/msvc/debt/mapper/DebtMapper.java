package com.nttdata.emeal.msvc.debt.mapper;

import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.repository.DebtRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebtMapper {

  @Autowired
  private DebtRepository debtRepository;

  public Single<Debt> mapDebtAndSave(Debt debtFounded, Debt debtDTO) {
    debtFounded.setAmount(debtDTO.getAmount());
    debtFounded.setIdProduct(debtDTO.getIdProduct());
    debtFounded.setExpirationDate(debtDTO.getExpirationDate());
    return debtRepository.save(debtFounded);
  }
}
