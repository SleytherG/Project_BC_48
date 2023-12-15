package com.nttdata.emeal.msvc.product.model;


import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Withdrawal {

  private BigDecimal amount;
  private String sourceProductId;

}
