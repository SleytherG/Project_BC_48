package com.nttdata.emeal.msvc.p2p.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellCryptoCurrencyDTO {

  private String amount;
  private String paymentMode;

}
