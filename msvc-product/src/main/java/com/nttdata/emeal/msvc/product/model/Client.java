package com.nttdata.emeal.msvc.product.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Client {

  private String id;
  private String documentNumber;
  private String documentType;
  private String phone;
  private String email;
  private String names;
  private String surnames;
  private String businessName;
  private String clientType;

}
