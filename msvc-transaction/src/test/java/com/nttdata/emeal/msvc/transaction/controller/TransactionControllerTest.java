package com.nttdata.emeal.msvc.transaction.controller;

import com.nttdata.emeal.msvc.transaction.model.Transaction;
import com.nttdata.emeal.msvc.transaction.services.TransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static com.nttdata.emeal.msvc.transaction.utils.Constants.DEPOSIT;
import static com.nttdata.emeal.msvc.transaction.utils.Constants.TRANSFER;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class TransactionControllerTest {

  private Transaction transaction;
  private Transaction transactionUpdate;

  @Mock
  private TransactionService transactionService;

  @InjectMocks
  private TransactionController transactionController;

  @BeforeEach
  void setUp() {
    this.transaction = Transaction.builder()
      .id("1")
      .dateTransaction(new Date().toString())
      .transactionType(DEPOSIT)
      .build();

    this.transactionUpdate = Transaction.builder()
      .id("2")
      .dateTransaction(new Date().toString())
      .transactionType(TRANSFER)
      .build();
  }



  @Test
  @Order(2)
  void testRetrieveAllTransactions() {
    // Arrange
    Mockito.when(transactionService.retrieveAllTransactions()).thenReturn(Flowable.just(transaction));

    // Act
    var testObserver = transactionController.retrieveAllTransactions().test();

    //Assert
    Mockito.verify(transactionService).retrieveAllTransactions();
    testObserver.assertComplete().assertNoErrors();

  }


  @Test
  @Order(3)
  void testRetrieveATransaction() {
    //Arrange
    Mockito.when(transactionService.retrieveATransaction("1")).thenReturn(Maybe.just(transaction));


    //Act
    Transaction transactionFounded = transactionController.retrieveATransaction("1").blockingGet();
    var testObserver = transactionController.retrieveATransaction("1").test();


    // Assert
    testObserver.assertComplete().assertNoErrors();
    assertNotNull(transactionFounded);
    assertEquals("1", transactionFounded.getId());
    assertEquals("DEPOSITO", transactionFounded.getTransactionType());
    assertEquals(new Date().toString(), transactionFounded.getDateTransaction());
  }

  @Test
  @Order(1)
  void testSaveTransaction() {
    // Arrange
    Mockito.when(transactionService.saveTransaction(transaction)).thenReturn(Single.just(transaction));

    // Act
    var testObserver = transactionController.saveTransaction(transaction).test();

    // Assert
    Mockito.verify(transactionService).saveTransaction(transaction);
    testObserver.assertComplete().assertNoErrors();
    assertEquals("1", transaction.getId());
    assertEquals("DEPOSITO", transaction.getTransactionType());
    assertEquals(new Date().toString(), transaction.getDateTransaction());
  }

  @Test
  @Order(4)
  void testUpdateTransaction() {
    // Arrange
    Mockito.when(transactionService.updateTransaction(transaction.getId(), transaction)).thenReturn(Single.just(transactionUpdate));

    // Act
    Transaction transactionUpdated = transactionController.updateTransaction(transaction.getId(), transaction).blockingGet();
    var testObserver = transactionController.updateTransaction(transaction.getId(), transaction).test();

    // Assert
    testObserver.assertComplete().assertNoErrors();
    assertNotNull(transactionUpdated);
    assertEquals("2", transactionUpdated.getId());
    assertEquals(new Date().toString(), transactionUpdated.getDateTransaction());
    assertEquals("TRANSFERENCIA", transactionUpdated.getTransactionType());
  }

  @Test
  @Order(5)
  void testDeleteTransaction() {
    // Arrange
    Mockito.when(transactionService.deleteTransaction(transaction.getId())).thenReturn(Completable.complete());


    // Act
    Completable result = transactionController.deleteTransaction(transaction.getId()).andThen(Completable.complete());
    TestObserver<Void> testObserver = result.test();

    // Assert
    testObserver.assertComplete().assertNoErrors();
    Mockito.verify(transactionService).deleteTransaction(transaction.getId());
  }
}