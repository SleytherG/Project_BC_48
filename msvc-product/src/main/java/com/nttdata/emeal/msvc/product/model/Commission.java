package com.nttdata.emeal.msvc.product.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Commission {

  private String id;
  private String productId;
  private BigDecimal amount;

}
