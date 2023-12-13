package com.nttdata.emeal.msvc.debt.controller;

import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.service.DebtService;
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
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DebtControllerTest {

  private String debtId;

  @Mock
  private DebtService debtService;

  @InjectMocks
  private DebtController debtController;


  @BeforeEach
  void setUp() {
    this.debtId = "123";
  }

  @Test
  void testRetrieveAllDebts()  {
    // Arrange
    when(debtService.retrieveAllDebts()).thenReturn(Flowable.empty());

    TestSubscriber<Debt> testSubscriber = new TestSubscriber<>();

    // Act
    debtController.retrieveAllDebts().subscribe(testSubscriber);

    // Assert
    testSubscriber.assertComplete();
    testSubscriber.assertNoErrors();
    testSubscriber.assertValueCount(0);
    verify(debtService, times(1)).retrieveAllDebts();
  }

  @Test
  void testRetrieveADebt() {
    // Arrange
    when(debtService.retrieveADebt(debtId)).thenReturn(Maybe.empty());
    TestObserver<Debt> testObserver = new TestObserver<>();

    // Act
    debtController.retrieveADebt(debtId).subscribe(testObserver);

    // Assert
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    testObserver.assertValueCount(0);
    verify(debtService, times(1)).retrieveADebt(debtId);

  }

  @Test
  void testSaveDebt() {
    // Arrange
    Debt debt = new Debt();
    when(debtService.saveDebt(debt)).thenReturn(Single.just(debt));
    TestObserver<Debt> testObserver = new TestObserver<>();

    // Act
    debtController.saveDebt(debt).subscribe(testObserver);

    // Assert
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    testObserver.assertValueCount(1);
    verify(debtService, times(1)).saveDebt(debt);
  }

  @Test
  void testUpdateDebt() {
    // Arrange
    Debt updatedDebt = new Debt();
    when(debtService.updateDebt(debtId, updatedDebt)).thenReturn(Single.just(updatedDebt));
    TestObserver<Debt> testObserver = new TestObserver<Debt>();

    // Act
    debtController.updateDebt(debtId, updatedDebt).subscribe(testObserver);

    // Assert
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    testObserver.assertValueCount(1);
    verify(debtService, times(1)).updateDebt(debtId, updatedDebt);
  }

  @Test
  void testDeleteDebt() {
    // Arrange
    when(debtService.deleteDebt(debtId)).thenReturn(Completable.complete());
    TestObserver<Void> testObserver = new TestObserver<>();

    // Act
    debtController.deleteDebt(debtId).subscribe(testObserver);

    // Assert
    testObserver.assertComplete();
    testObserver.assertNoErrors();
    verify(debtService, times(1)).deleteDebt(debtId);
  }
}