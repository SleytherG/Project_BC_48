package com.nttdata.emeal.msvc.product.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChargeConsumptionToCreditCardDTO {

  private BigDecimal charge;
  private String productId;

}
