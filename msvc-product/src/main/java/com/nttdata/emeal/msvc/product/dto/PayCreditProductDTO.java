package com.nttdata.emeal.msvc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayCreditProductDTO {

  private BigDecimal amount;
  private String productId;

}
