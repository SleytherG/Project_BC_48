package com.nttdata.emeal.msvc.p2p.services;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyTransactionDTO;
import com.nttdata.emeal.msvc.p2p.dto.TransactionNumberDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrencyTransaction;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface CryptoCurrencyTransactionService {
  Flowable<CryptoCurrencyTransaction> retrieveAllCryptoCurrencyTransaction();

  Maybe<CryptoCurrencyTransaction> retrieveACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId);

  Single<CryptoCurrencyTransaction> saveACryptoCurrencyTransaction(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO);

  Single<CryptoCurrencyTransaction> updateACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId, CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO);

  Completable deleteACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId);

  Completable requestCryptoCurrencyPurchase(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO);

  Single<TransactionNumberDTO> approveCryptoCurrencyPurchase(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO);
}
