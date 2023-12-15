package com.nttdata.emeal.msvc.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCard extends BankCredit {

  private String expirationDate;
  private String holderName;
  private String totalLine;
  private BigDecimal currentLine;
  private BigDecimal currentBalance;
  private String cutOffDate;
  private String paymentDate;
  private BigDecimal minPayment;
  private BigDecimal totalPayment;

}
