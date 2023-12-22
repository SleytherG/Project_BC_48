package com.nttdata.emeal.msvc.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDTO implements Serializable {

  private String id;
  private String documentNumber;
  private String documentType;
  private String phone;
  private String email;
  private String clientType;
}
