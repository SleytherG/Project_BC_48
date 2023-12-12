package com.nttdata.emeal.msvc.transaction.repository;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface TransactionRepository extends RxJava3CrudRepository<Transaction, String> {
}
