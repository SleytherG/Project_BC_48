package com.nttdata.emeal.msvc.transaction.services.impl;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import com.nttdata.emeal.msvc.transaction.repository.TransactionRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static com.nttdata.emeal.msvc.transaction.utils.Constants.DEPOSIT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private TransactionServiceImpl transactionService;

  private Transaction transaction;

  private Transaction emptyTransaction;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    transaction = new Transaction("1", new Date().toString(), DEPOSIT, "123");
    emptyTransaction = new Transaction();
  }

  @Test
  void retrieveAllTransactions() {
    // Arrange
    Mockito.when(transactionRepository.findAll()).thenReturn(Flowable.just(transaction));

    // Act
    Flowable<Transaction> result = transactionService.retrieveAllTransactions();

    // Assert
    assertNotNull(result);
    TestSubscriber<Transaction> testSubscriber = result.test();
    testSubscriber.assertValue(transaction -> {
      assertEquals("1", transaction.getId());
      assertEquals(new Date().toString(), transaction.getDateTransaction());
      assertEquals("DEPOSITO", transaction.getTransactionType());
      return true;
    });
    testSubscriber.assertComplete();
    testSubscriber.assertNoErrors();
  }

  @Test
  void retrieveATransaction() {
    // Arrange
    Mockito.when(transactionRepository.findById(transaction.getId())).thenReturn(Maybe.just(transaction));

    // Act
    Maybe<Transaction> result = transactionService.retrieveATransaction(transaction.getId());

    // Assert
    assertNotNull(result);
    TestObserver<Transaction> testObserver = result.test();
    testObserver.assertValue(transaction -> {
      assertNotNull(transaction);
      assertEquals("1", transaction.getId());
      assertEquals(new Date().toString(), transaction.getDateTransaction());
      assertEquals("DEPOSITO", transaction.getTransactionType());
      return true;
    });
    testObserver.assertComplete();
    testObserver.assertNoErrors();

  }

  @Test
  void saveTransaction() {
    // Arrange
    Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(Single.just(transaction));

    // Act
    Single<Transaction> result = transactionService.saveTransaction(transaction);

    // Assert
    assertNotNull(result);
    TestObserver<Transaction> testObserver = result.test();
    testObserver.assertValue(transaction -> {
      assertNotNull(transaction);
      assertEquals("1", transaction.getId());
      assertEquals(new Date().toString(), transaction.getDateTransaction());
      assertEquals("DEPOSITO", transaction.getTransactionType());
      return true;
    });
    testObserver.assertComplete();
    testObserver.assertNoErrors();
  }

  @Test
  void updateTransaction() {
    // Arrange
    Mockito.when(transactionRepository.findById(transaction.getId())).thenReturn(Maybe.just(transaction));
    Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(Single.just(transaction));

    // Act
    Single<Transaction> result = transactionService.updateTransaction(transaction.getId(), transaction);


    // Assert
    assertNotNull(result);
    TestObserver<Transaction> testObserver = result.test();
    testObserver.assertValue(transaction -> {
      assertNotNull(transaction);
      assertEquals("1", transaction.getId());
      assertEquals(new Date().toString(), transaction.getDateTransaction());
      assertEquals("DEPOSITO", transaction.getTransactionType());
      return true;
    });
    testObserver.assertComplete();
    testObserver.assertNoErrors();
  }

  @Test
  void deleteTransaction() {
    // Arrange
    Mockito.when(transactionRepository.findById(transaction.getId())).thenReturn(Maybe.just(transaction));
    Mockito.when(transactionRepository.deleteById(transaction.getId())).thenReturn(Completable.complete());

    // Act
    Completable result = transactionService.deleteTransaction(transaction.getId());

    // Assert
    assertNotNull(result);
    TestObserver<Void> testObserver = result.test();
    testObserver.assertComplete();
    testObserver.assertNoErrors();
  }
}