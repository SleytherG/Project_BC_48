package com.nttdata.emeal.msvc.p2p.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "cryptocurrencies")
public class CryptoCurrency {

  @Id
  private String id;
  private String name;
  private BigDecimal purchaseRate;
  private BigDecimal salesRate;
}
