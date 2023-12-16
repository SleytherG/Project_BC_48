package com.nttdata.emeal.msvc.product.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BankTransferToOtherAccountDTO {

  private BigDecimal amount;
  private String sourceProductId;
  private String targetProductId;
}
