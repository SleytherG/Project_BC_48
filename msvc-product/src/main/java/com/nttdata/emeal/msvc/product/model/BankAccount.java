package com.nttdata.emeal.msvc.product.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Product {

  private String accountBankType;
  private BigDecimal balance;

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
}
