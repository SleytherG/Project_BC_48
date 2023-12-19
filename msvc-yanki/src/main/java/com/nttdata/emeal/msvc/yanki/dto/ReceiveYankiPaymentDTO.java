package com.nttdata.emeal.msvc.yanki.dto;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReceiveYankiPaymentDTO {

  private BigDecimal amount;
  private String sourceYankiId;
  private String targetYankiId;
}
