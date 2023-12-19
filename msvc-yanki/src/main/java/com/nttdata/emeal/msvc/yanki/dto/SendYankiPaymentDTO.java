package com.nttdata.emeal.msvc.yanki.dto;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SendYankiPaymentDTO {

  private String sourceYankiId;
  private String targetYankiId;
  private BigDecimal amount;
}
