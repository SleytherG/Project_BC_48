package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.Transaction;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface TransactionService {

  Flowable<Transaction> getAllTransactionsByProductId(String productId);

  Flowable<Transaction> getLastTenTransactionsByProductId(String productId);

  Single<Transaction> saveATransaction(Transaction transaction);
}
