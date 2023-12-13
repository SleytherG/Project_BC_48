package com.nttdata.emeal.msvc.debt.mapper;

import com.nttdata.emeal.msvc.debt.model.Debt;
import com.nttdata.emeal.msvc.debt.repository.DebtRepository;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DebtMapperTest {

  @Mock
  private DebtRepository debtRepository;

  @InjectMocks
  private DebtMapper debtMapper;

  @Test
  void testMapDebtAndSave() {
    // Arrange
    Debt existingDebt = new Debt();
    Debt updatedDebtDTO = new Debt();

    when(debtRepository.save(any())).thenReturn(Single.just(existingDebt));

    // Act
    Single<Debt> result = debtMapper.mapDebtAndSave(existingDebt, updatedDebtDTO);

    // Assert
    verify(debtRepository, times(1)).save(existingDebt);
    result.test().assertValue(updatedDebt -> {
      assertEquals(updatedDebt.getAmount(), updatedDebtDTO.getAmount());
      assertEquals(updatedDebt.getIdProduct(), updatedDebtDTO.getIdProduct());
      assertEquals(updatedDebt.getExpirationDate(), updatedDebtDTO.getExpirationDate());
      return true;
    });
  }
}