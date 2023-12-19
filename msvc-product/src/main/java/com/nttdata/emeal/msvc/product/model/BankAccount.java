package com.nttdata.emeal.msvc.product.model;

import com.nttdata.emeal.msvc.product.service.CommissionService;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Product {

  private String accountBankType;
  private BigDecimal balance;
  private Integer maxTransactionLimit = 20;
  private Boolean isMainAccount;

  public void deposit(BigDecimal amount) {
    this.balance = this.balance.add(amount);
  }

  public void withdrawal(BigDecimal amount) {
    BigDecimal result = this.balance.subtract(amount);
    if ( result.compareTo(BigDecimal.ZERO) < 0 ) {
      throw new IllegalArgumentException("Not enough balance to do a withdrawal");
    }
    this.balance = result;
  }

  public void doATransaction() {
    if ( this.maxTransactionLimit <= 0) {
      this.maxTransactionLimit = 0;
      return;
    }
    this.maxTransactionLimit = this.maxTransactionLimit - 1;
  }

  public BigDecimal transactionFee() {
    BigDecimal feePercentage = BigDecimal.valueOf(100.0 / 45.0);
    BigDecimal feeAmount = this.balance.multiply(feePercentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    this.balance = this.balance.subtract(feeAmount);
    return feeAmount;
  }


}
