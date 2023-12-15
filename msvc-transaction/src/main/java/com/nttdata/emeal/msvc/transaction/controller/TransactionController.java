package com.nttdata.emeal.msvc.transaction.controller;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import com.nttdata.emeal.msvc.transaction.services.TransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TransactionController.TRANSACTIONS)
public class TransactionController {

  public static final String TRANSACTIONS = "/transactions";
  public static final String TRANSACTION_ID = "/{transactionId}";
  public static final String PRODUCT_ID = "/{productId}";
  public static final String GET_ALL_TRANSACTIONS_BY_PRODUCT_ID = "/getAllTransactionsByProductId";

  @Autowired
  private TransactionService transactionsService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Transaction> retrieveAllTransactions() {
    return transactionsService.retrieveAllTransactions();
  }

  @GetMapping(TRANSACTION_ID)
  public Maybe<Transaction> retrieveATransaction(@PathVariable String transactionId) {
    return transactionsService.retrieveATransaction(transactionId);
  }

  @PostMapping(produces = {"application/json"})
  public Single<Transaction> saveTransaction(@RequestBody Transaction transaction) {
    return transactionsService.saveTransaction(transaction);
  }

  @PutMapping(TRANSACTION_ID)
  public Single<Transaction> updateTransaction(@PathVariable String transactionId, @RequestBody Transaction transaction) {
    return transactionsService.updateTransaction(transactionId, transaction);
  }

  @DeleteMapping(TRANSACTION_ID)
  public Completable deleteTransaction(@PathVariable String transactionId) {
    return transactionsService.deleteTransaction(transactionId);
  }

  @GetMapping(GET_ALL_TRANSACTIONS_BY_PRODUCT_ID + PRODUCT_ID)
  public Flowable<Transaction> getAllTransactionsByProductId(@PathVariable String productId) {
    return transactionsService.getAllTransactionsByProductId(productId);
  }

}
