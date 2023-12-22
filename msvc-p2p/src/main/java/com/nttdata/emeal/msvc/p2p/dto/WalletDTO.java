package com.nttdata.emeal.msvc.p2p.dto;


import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletDTO {

  private String id;
  private String documentNumber;
  private String documentType;
  private String phoneNumber;
  private String email;
  private List<CryptoCurrency> cryptocurrencies;

}
