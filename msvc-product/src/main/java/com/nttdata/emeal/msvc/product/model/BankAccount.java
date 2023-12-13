package com.nttdata.emeal.msvc.product.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Product {

  private String accountBankType;
  private String balance;
}
