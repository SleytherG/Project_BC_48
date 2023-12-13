package com.nttdata.emeal.msvc.debt.service.impl;

import com.nttdata.emeal.msvc.debt.exceptions.DebtNotFoundException;
import com.nttdata.emeal.msvc.debt.mapper.DebtMapper;
import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.repository.DebtRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DebtServiceImplTest {

  @Mock
  private DebtRepository debtRepository;

  @Mock
  private DebtMapper debtMapper;

  @InjectMocks
  private DebtServiceImpl debtService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void retrieveAllDebts() {
    // Arrange
    when(debtRepository.findAll()).thenReturn(Flowable.just(new Debt(), new Debt()));

    // Act
    Flowable<Debt> result = debtService.retrieveAllDebts();

    // Assert
    assertNotNull(result);
    assertEquals(2, result.count().blockingGet()); // Adjust based on your actual logic
  }

  @Test
  void retrieveADebt() {
    // Arrange
    String debtId = "123";
    when(debtRepository.findById(debtId)).thenReturn(Maybe.just(new Debt()));

    // Act
    Maybe<Debt> result = debtService.retrieveADebt(debtId);

    // Assert
    assertNotNull(result);
//    assertTrue(result.hasValue().blockingGet());
  }

  @Test
  void saveDebt() {
    // Arrange
    Debt debt = new Debt();
    when(debtRepository.save(debt)).thenReturn(Single.just(debt));

    // Act
    Single<Debt> result = debtService.saveDebt(debt);

    // Assert
    assertNotNull(result);
    assertEquals(debt, result.blockingGet());
  }

  @Test
  void updateDebt() {
    // Arrange
    String debtId = "123";
    Debt existingDebt = new Debt();
    Debt updatedDebt = new Debt();
    when(debtRepository.findById(debtId)).thenReturn(Maybe.just(existingDebt));
    when(debtMapper.mapDebtAndSave(existingDebt, updatedDebt)).thenReturn(Single.just(updatedDebt));

    // Act
    Single<Debt> result = debtService.updateDebt(debtId, updatedDebt);

    // Assert
    assertNotNull(result);
    assertEquals(updatedDebt, result.blockingGet());
  }

  @Test
  void deleteDebt() {
    // Arrange
    String debtId = "123";
    Debt existingDebt = new Debt();
    when(debtRepository.findById(debtId)).thenReturn(Maybe.just(existingDebt));
    when(debtRepository.deleteById(debtId)).thenReturn(Completable.complete());

    // Act
    Completable result = debtService.deleteDebt(debtId);

    // Assert
    assertNotNull(result);
    assertDoesNotThrow(() -> result.blockingAwait());
    verify(debtRepository, times(1)).deleteById(debtId);
  }
}