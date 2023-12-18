package com.nttdata.emeal.msvc.transaction.services;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface TransactionService {

  Flowable<Transaction> retrieveAllTransactions();
  Maybe<Transaction> retrieveATransaction(String transactionId);
  Single<Transaction> saveTransaction(Transaction transaction);
  Single<Transaction> updateTransaction(String transactionId, Transaction transaction);
  Completable deleteTransaction(String transactionId);

  Flowable<Transaction> getAllTransactionsByProductId(String productId);

  Flowable<Transaction> getLastTenTransactionsByProductId(String productId);
}
