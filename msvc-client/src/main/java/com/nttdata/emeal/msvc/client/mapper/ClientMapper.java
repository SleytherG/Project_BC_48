package com.nttdata.emeal.msvc.client.mapper;

import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

  public Client mapToClientFromPersonalClient(PersonalClient personalClient) {
    return Client.builder()
      .id(personalClient.getId())
      .documentNumber(personalClient.getDocumentNumber())
      .documentType(personalClient.getDocumentType())
      .phone(personalClient.getPhone())
      .email(personalClient.getEmail())
      .build();
  }

  public PersonalClient mapToPersonalClientFromClient(PersonalClient personalClientDTO) {
    PersonalClient personalClient = new PersonalClient();
    BeanUtils.copyProperties(personalClientDTO, personalClient);
    return  personalClient;
  }

  public EnterpriseClient mapToEnterpriseClientFromClient(EnterpriseClient enterpriseClientDTO) {
    EnterpriseClient enterpriseClient = new EnterpriseClient();
    BeanUtils.copyProperties(enterpriseClientDTO, enterpriseClient);
    return  enterpriseClient;
  }


}
