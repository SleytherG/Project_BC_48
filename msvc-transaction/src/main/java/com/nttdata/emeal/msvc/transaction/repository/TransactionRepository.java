package com.nttdata.emeal.msvc.transaction.repository;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface TransactionRepository extends RxJava3CrudRepository<Transaction, String> {
  
  Flowable<Transaction> getTransactionsByProductId(String productId);

}
