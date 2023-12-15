package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.Transaction;
import io.reactivex.rxjava3.core.Flowable;

public interface TransactionService {

  Flowable<Transaction> getAllTransactionsByProductId(String productId);
}
