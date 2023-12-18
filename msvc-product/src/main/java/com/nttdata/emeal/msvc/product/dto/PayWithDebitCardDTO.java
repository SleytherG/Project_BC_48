package com.nttdata.emeal.msvc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayWithDebitCardDTO {

  private BigDecimal amountToPay;
  private String sourceProductId;
  private String targetProductId;
}
