package com.nttdata.emeal.msvc.transaction.services.impl;

import com.nttdata.emeal.msvc.transaction.exceptions.TransactionNotFoundException;
import com.nttdata.emeal.msvc.transaction.model.Transaction;
import com.nttdata.emeal.msvc.transaction.repository.TransactionRepository;
import com.nttdata.emeal.msvc.transaction.services.TransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public Flowable<Transaction> retrieveAllTransactions() {
    return transactionRepository.findAll();
  }

  @Override
  public Maybe<Transaction> retrieveATransaction(String transactionId) {
    return transactionRepository.findById(transactionId);
  }

  @Override
  public Single<Transaction> saveTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  @Override
  public Single<Transaction> updateTransaction(String transactionId, Transaction transaction) {
    return transactionRepository
      .findById(transactionId)
      .switchIfEmpty(Single.error(new TransactionNotFoundException("Transaction not found with id: " + transactionId)))
      .flatMap(transactionFounded -> {
        BeanUtils.copyProperties(transaction, transactionFounded, "id");
        return transactionRepository.save(transactionFounded);
      });
  }

  @Override
  public Completable deleteTransaction(String transactionId) {
    return transactionRepository
      .findById(transactionId)
      .switchIfEmpty(Maybe.error(new TransactionNotFoundException("Transaction not found with id: " + transactionId)))
      .flatMapCompletable(transaction -> Completable.fromAction(() -> transactionRepository.deleteById(transactionId).subscribe()))
      .doOnComplete(() -> log.info("Transaction deleted"));
  }

  @Override
  public Flowable<Transaction> getAllTransactionsByProductId(String productId) {
    return transactionRepository.getTransactionsByProductId(productId);
  }
}
