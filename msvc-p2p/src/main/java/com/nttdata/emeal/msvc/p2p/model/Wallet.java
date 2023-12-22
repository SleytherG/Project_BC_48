package com.nttdata.emeal.msvc.p2p.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "wallets")
public class Wallet {

  @Id
  private String id;
  private String documentNumber;
  private String documentType;
  private String phoneNumber;
  private String email;
  private List<CryptoCurrency> cryptocurrencies;

}
