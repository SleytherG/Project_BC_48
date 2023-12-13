package com.nttdata.emeal.msvc.client.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalClient extends Client {

  private String names;
  private String surnames;
}
