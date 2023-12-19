package com.nttdata.emeal.msvc.yanki.services;

import com.nttdata.emeal.msvc.yanki.dto.AssociateAccountDTO;
import com.nttdata.emeal.msvc.yanki.dto.ReceiveYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.SendYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.YankiDTO;
import com.nttdata.emeal.msvc.yanki.model.Yanki;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface YankiService {
  Flowable<Yanki> retrieveAllYankis();

  Maybe<Yanki> retrieveAYanki(String yankiId);

  Single<Yanki> saveYanki(YankiDTO yankiDTO);

  Single<Yanki> updateYanki(String yankiId, YankiDTO yankiDTO);

  Completable deleteYanki(String yankiId);

  Completable receiveYankiPayment(ReceiveYankiPaymentDTO receiveYankiPaymentDTO);

  Completable sendYankiPayment(SendYankiPaymentDTO sendYankiPaymentDTO);

  Completable associateAccountToYanki(AssociateAccountDTO associateAccountDTO);
}
