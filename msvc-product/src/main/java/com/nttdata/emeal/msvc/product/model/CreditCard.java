package com.nttdata.emeal.msvc.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCard extends BankCredit {

  private String expirationDate;
  private String holderName;
  private String totalLine;
  private String currentLine;
  private String currentBalance;
  private String cutOffDate;
  private String paymentDate;
  private String minPayment;
  private String totalPayment;

}
