package com.nttdata.emeal.msvc.product.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankCredit extends Product {

  private String numCredits;
  private String creditType;
  private BigDecimal totalDebt;
}
