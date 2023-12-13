package com.nttdata.emeal.msvc.product.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankCredit extends Product {

  private String numCredits;
  private String creditType;
}
