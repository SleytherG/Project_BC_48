package com.nttdata.emeal.msvc.yanki.controller;

import com.nttdata.emeal.msvc.yanki.dto.AssociateAccountDTO;
import com.nttdata.emeal.msvc.yanki.dto.ReceiveYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.SendYankiPaymentDTO;
import com.nttdata.emeal.msvc.yanki.dto.YankiDTO;
import com.nttdata.emeal.msvc.yanki.model.Yanki;
import com.nttdata.emeal.msvc.yanki.services.YankiService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(YankiController.YANKIS)
public class YankiController {

  public static final String YANKIS = "/yankis";
  public static final String YANKI_ID = "/{yankiId}";

  public static final String RECEIVE_YANKI_PAYMENT = "/receievYankiPayment";
  public static final String SEND_YANKI_PAYMENT = "/sendYankiPayment";
  public static final String ASSOCIATE_ACCOUNT_TO_YANKI = "/associateAccountToYanki";

  @Autowired
  private YankiService yankiService;

  @GetMapping
  public Flowable<Yanki> retrieveAllYankis() {
     return yankiService.retrieveAllYankis();
  }

  @GetMapping(YANKI_ID)
  public Maybe<Yanki> retrieveAYanki(@PathVariable String yankiId) {
    return yankiService.retrieveAYanki(yankiId);
  }

  @PostMapping
  public Single<Yanki> saveYanki(@RequestBody YankiDTO yankiDTO) {
    return yankiService.saveYanki(yankiDTO);
  }
  
  @PutMapping(YANKI_ID)
  public Single<Yanki> updateYanki(@PathVariable String yankiId, @RequestBody YankiDTO yankiDTO) {
    return yankiService.updateYanki(yankiId, yankiDTO);
  }
  
  @DeleteMapping(YANKI_ID)
  public Completable deleteYanki(@PathVariable String yankiId) {
    return yankiService.deleteYanki(yankiId);
  }

  @PostMapping(RECEIVE_YANKI_PAYMENT)
  public Completable receiveYankiPayment(@RequestBody ReceiveYankiPaymentDTO receiveYankiPaymentDTO) {
    return yankiService.receiveYankiPayment(receiveYankiPaymentDTO);
  }

  @PostMapping(SEND_YANKI_PAYMENT)
  public Completable sendYankiPayment(@RequestBody SendYankiPaymentDTO sendYankiPaymentDTO) {
    return yankiService.sendYankiPayment(sendYankiPaymentDTO);
  }

  @PostMapping(ASSOCIATE_ACCOUNT_TO_YANKI)
  public Completable associateAccountToYanki(@RequestBody AssociateAccountDTO associateAccountDTO) {
    return yankiService.associateAccountToYanki(associateAccountDTO);
  }



}
