package com.nttdata.emeal.msvc.product.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

  private String id;
  private String idClient;
  private String productType;
  private String accountBankType;
  private BigDecimal balance;
  private String numCredits;
  private String creditType;
}
