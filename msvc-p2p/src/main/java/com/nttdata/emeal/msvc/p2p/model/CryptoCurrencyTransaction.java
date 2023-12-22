package com.nttdata.emeal.msvc.p2p.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "cryptocurrency-transactions")
public class CryptoCurrencyTransaction {

  @Id
  private String id;
  private String sourceUserId;
  private String targetUserId;
  private BigDecimal amount;
  private String paymentMode;
  private String transactionNumber;
  private Date transactionDate;
  private Integer status;
}
