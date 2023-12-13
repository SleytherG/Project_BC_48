package com.nttdata.emeal.msvc.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanBank extends BankCredit {

  private String amount;
  private String dateCreated;
}
