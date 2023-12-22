package com.nttdata.emeal.msvc.p2p.services.impl;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyTransactionDTO;
import com.nttdata.emeal.msvc.p2p.dto.TransactionNumberDTO;
import com.nttdata.emeal.msvc.p2p.exceptions.CryptoCurrencyTransactionNotFoundException;
import com.nttdata.emeal.msvc.p2p.mapper.CryptoCurrencyTransactionMapper;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrencyTransaction;
import com.nttdata.emeal.msvc.p2p.repository.CryptoCurrencyTransactionRepository;
import com.nttdata.emeal.msvc.p2p.services.CryptoCurrencyTransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.nttdata.emeal.msvc.p2p.utils.Constants.APPROVED;
import static com.nttdata.emeal.msvc.p2p.utils.Constants.PENDING;

@Service
public class CryptoCurrencyTransactionServiceImpl implements CryptoCurrencyTransactionService {

  @Autowired
  private CryptoCurrencyTransactionRepository cryptoCurrencyTransactionRepository;

  @Autowired
  private CryptoCurrencyTransactionMapper cryptoCurrencyTransactionMapper;

  @Override
  public Flowable<CryptoCurrencyTransaction> retrieveAllCryptoCurrencyTransaction() {
    return cryptoCurrencyTransactionRepository.findAll();
  }

  @Override
  public Maybe<CryptoCurrencyTransaction> retrieveACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId) {
    return cryptoCurrencyTransactionRepository
      .findById(cryptoCurrenciesTransactionId)
      .switchIfEmpty(Maybe.error(new CryptoCurrencyTransactionNotFoundException("CryptoCurrencyTransaction not found with id: " + cryptoCurrenciesTransactionId)));
  }

  @Override
  public Single<CryptoCurrencyTransaction> saveACryptoCurrencyTransaction(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionRepository
      .save(cryptoCurrencyTransactionMapper.mapFromCryptoCurrencyTransactionDTOToCryptoCurrencyTransaction(cryptoCurrencyTransactionDTO));
  }

  @Override
  public Single<CryptoCurrencyTransaction> updateACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId, CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionRepository
      .findById(cryptoCurrenciesTransactionId)
      .switchIfEmpty(Single.error(new CryptoCurrencyTransactionNotFoundException("CryptoCurrencyTransaction not found with id: " + cryptoCurrenciesTransactionId)))
      .flatMap(cryptoCurrencyTransaction -> cryptoCurrencyTransactionRepository.save(cryptoCurrencyTransactionMapper.mapFromCryptoCurrencyTransactionToSave(cryptoCurrencyTransaction, cryptoCurrencyTransactionDTO)));
  }

  @Override
  public Completable deleteACryptoCurrencyTransaction(String cryptoCurrenciesTransactionId) {
    return cryptoCurrencyTransactionRepository
      .findById(cryptoCurrenciesTransactionId)
      .switchIfEmpty(Single.error(new CryptoCurrencyTransactionNotFoundException("CryptoCurrencyTransaction not found with id: " + cryptoCurrenciesTransactionId)))
      .flatMapCompletable(cryptoCurrencyTransaction -> Completable.fromAction(() -> cryptoCurrencyTransactionRepository.deleteById(cryptoCurrencyTransaction.getId()).subscribe()));
  }

  @Override
  public Completable requestCryptoCurrencyPurchase(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return Completable.fromAction(() -> {
      cryptoCurrencyTransactionDTO.setStatus(PENDING);
      saveACryptoCurrencyTransaction(cryptoCurrencyTransactionDTO).subscribe();
    });
  }

  @Override
  public Single<TransactionNumberDTO> approveCryptoCurrencyPurchase(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return cryptoCurrencyTransactionRepository
      .findById(cryptoCurrencyTransactionDTO.getId())
      .switchIfEmpty(Single.error(new CryptoCurrencyTransactionNotFoundException("CryptoCurrencyTransaction not found with id: " + cryptoCurrencyTransactionDTO.getId())))
      .flatMap(cryptoCurrencyTransaction -> {
        cryptoCurrencyTransaction.setStatus(APPROVED);
        cryptoCurrencyTransaction.setTransactionDate(new Date());
        return cryptoCurrencyTransactionRepository.save(cryptoCurrencyTransaction);
      })
      .flatMap(cryptoCurrencyTransaction -> Single.just(new TransactionNumberDTO(cryptoCurrencyTransaction.getTransactionNumber())));
  }
}
