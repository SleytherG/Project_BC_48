package com.nttdata.emeal.msvc.p2p.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CryptoCurrencyDTO {

  private String id;
  private String name;
  private BigDecimal purchaseRate;
  private BigDecimal salesRate;
}
