package com.nttdata.emeal.msvc.p2p.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CryptoCurrencyTransactionDTO {

  private String id;
  private String sourceUserId;
  private String targetUserId;
  private BigDecimal amount;
  private String paymentMode;
  private String transactionNumber;
  private Date transactionDate;
  private Integer status;
}
