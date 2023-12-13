package com.nttdata.emeal.msvc.client.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "clients")
public class Client {

  @Id
  private String id;
  private String documentNumber;
  private String documentType;
  private String phone;
  private String email;

}
