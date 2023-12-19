package com.nttdata.emeal.msvc.yanki.services.impl;

import com.nttdata.emeal.msvc.yanki.dto.AssociateAccountDTO;
import com.nttdata.emeal.msvc.yanki.dto.ReceiveYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.SendYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.YankiDTO;
import com.nttdata.emeal.msvc.yanki.exceptions.YankiNotFoundException;
import com.nttdata.emeal.msvc.yanki.mapper.YankiMapper;
import com.nttdata.emeal.msvc.yanki.model.BankAccount;
import com.nttdata.emeal.msvc.yanki.model.Yanki;
import com.nttdata.emeal.msvc.yanki.repository.YankiRepository;
import com.nttdata.emeal.msvc.yanki.services.ProductService;
import com.nttdata.emeal.msvc.yanki.services.YankiService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YankiServiceImpl implements YankiService {

  @Autowired
  private YankiRepository yankiRepository;

  @Autowired
  private YankiMapper yankiMapper;

  @Autowired
  private ProductService productService;


  @Override
  public Flowable<Yanki> retrieveAllYankis() {
    return yankiRepository.findAll();
  }

  @Override
  public Maybe<Yanki> retrieveAYanki(String yankiId) {
    return yankiRepository.findById(yankiId);
  }

  @Override
  public Single<Yanki> saveYanki(YankiDTO yankiDTO) {
    return yankiRepository.save(yankiMapper.mapYankiDTOToYanki(yankiDTO));
  }

  @Override
  public Single<Yanki> updateYanki(String yankiId, YankiDTO yankiDTO) {
    return yankiRepository
      .findById(yankiId)
      .switchIfEmpty(Single.error(new YankiNotFoundException("Yanki not found with id: " + yankiId)))
      .flatMap(yankiFounded -> yankiRepository.save(yankiMapper.mapYankiDTOAndUpdate(yankiFounded, yankiDTO)));
  }

  @Override
  public Completable deleteYanki(String yankiId) {
    return yankiRepository
      .findById(yankiId)
      .switchIfEmpty(Maybe.error(new YankiNotFoundException("Yanki not found with id: " + yankiId)))
      .flatMapCompletable(yanki -> Completable.fromAction(() -> yankiRepository.deleteById(yankiId).subscribe()));
  }

  @Override
  public Completable receiveYankiPayment(ReceiveYankiPaymentDTO receiveYankiPaymentDTO) {
    return Completable.fromAction(() -> {
      Yanki sourceYanki = yankiRepository.findById(receiveYankiPaymentDTO.getSourceYankiId()).blockingGet();
      Yanki targetYanki = yankiRepository.findById(receiveYankiPaymentDTO.getTargetYankiId()).blockingGet();

      assert sourceYanki != null;
      sourceYanki.setBalance(sourceYanki.getBalance().add(receiveYankiPaymentDTO.getAmount()));
      assert targetYanki != null;
      targetYanki.setBalance(targetYanki.getBalance().subtract(receiveYankiPaymentDTO.getAmount()));

      yankiRepository.save(sourceYanki).subscribe();
      yankiRepository.save(targetYanki).subscribe();

    });
  }

  @Override
  public Completable sendYankiPayment(SendYankiPaymentDTO sendYankiPaymentDTO) {
    return Completable.fromAction(() -> {
      Yanki sourceYanki = yankiRepository.findById(sendYankiPaymentDTO.getSourceYankiId()).blockingGet();
      Yanki targetYanki = yankiRepository.findById(sendYankiPaymentDTO.getTargetYankiId()).blockingGet();

      assert sourceYanki != null;
      sourceYanki.setBalance(sourceYanki.getBalance().subtract(sendYankiPaymentDTO.getAmount()));
      assert targetYanki != null;
      targetYanki.setBalance(targetYanki.getBalance().add(sendYankiPaymentDTO.getAmount()));

      yankiRepository.save(sourceYanki).subscribe();
      yankiRepository.save(targetYanki).subscribe();
    });
  }

  @Override
  public Completable associateAccountToYanki(AssociateAccountDTO associateAccountDTO) {
    return Completable.fromAction(() -> {
      BankAccount mainClientAccount = productService.getProductsByClientId(associateAccountDTO.getClientId()).filter(BankAccount::getIsMainAccount).blockingFirst();
      Yanki yanki = yankiRepository.findById(associateAccountDTO.getYankiId()).blockingGet();

      assert yanki != null;
      yanki.setAssociatedAccount(mainClientAccount.getId());

      yankiRepository.save(yanki).subscribe();
    });
  }
}
