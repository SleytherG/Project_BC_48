package com.nttdata.emeal.msvc.p2p.controller;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyDTO;
import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyTransactionDTO;
import com.nttdata.emeal.msvc.p2p.dto.TransactionNumberDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrencyTransaction;
import com.nttdata.emeal.msvc.p2p.services.CryptoCurrencyTransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CryptoCurrencyTransactionController.P2P + CryptoCurrencyTransactionController.CRYPTOCURRENCY_TRANSACTIONS)
public class CryptoCurrencyTransactionController {

  public static final String P2P = "/p2p";
  public static final String CRYPTOCURRENCY_TRANSACTIONS = "/cryptoCurrencyTransactions";
  public static final String CRYPTOCURRENCY_TRANSACTION_ID = "/{cryptoCurrencyTransactionId}";
  public static final String REQUEST_CRYPTOCURRENCY_PURCHASE = "/requestCryptoCurrencyPurchase";
  public static final String APPROVE_CRYPTOCURRENCY_PURCHASE = "/approveCryptoCurrencyPurchase";


  @Autowired
  private CryptoCurrencyTransactionService cryptoCurrencyTransactionService;

  @GetMapping()
  public Flowable<CryptoCurrencyTransaction> retrieveAllCryptoCurrencyTransaction() {
    return cryptoCurrencyTransactionService.retrieveAllCryptoCurrencyTransaction();
  }

  @GetMapping(CRYPTOCURRENCY_TRANSACTION_ID)
  public Maybe<CryptoCurrencyTransaction> retrieveACryptoCurrencyTransaction(@PathVariable String cryptoCurrencyTransactionId) {
    return cryptoCurrencyTransactionService.retrieveACryptoCurrencyTransaction(cryptoCurrencyTransactionId);
  }

  @PostMapping()
  public Single<CryptoCurrencyTransaction> saveACryptoCurrencyTransaction(@RequestBody CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionService.saveACryptoCurrencyTransaction(cryptoCurrencyTransactionDTO);
  }

  @PutMapping(CRYPTOCURRENCY_TRANSACTION_ID)
  public Single<CryptoCurrencyTransaction> updateACryptoCurrencyTransaction(@PathVariable String cryptoCurrencyTransactionId, @RequestBody CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionService.updateACryptoCurrencyTransaction(cryptoCurrencyTransactionId, cryptoCurrencyTransactionDTO);
  }

  @DeleteMapping(CRYPTOCURRENCY_TRANSACTION_ID)
  public Completable deleteACryptoCurrencyTransaction(@PathVariable String cryptoCurrencyTransactionId) {
    return cryptoCurrencyTransactionService.deleteACryptoCurrencyTransaction(cryptoCurrencyTransactionId);
  }

  @PostMapping(REQUEST_CRYPTOCURRENCY_PURCHASE)
  public Completable requestCryptoCurrencyPurchase(@RequestBody CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionService.requestCryptoCurrencyPurchase(cryptoCurrencyTransactionDTO);
  }

  @PostMapping(APPROVE_CRYPTOCURRENCY_PURCHASE)
  public Single<TransactionNumberDTO> approveCryptoCurrencyPurchase(@RequestBody CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionService.approveCryptoCurrencyPurchase(cryptoCurrencyTransactionDTO);
  }

}
