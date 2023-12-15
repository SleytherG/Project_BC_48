package com.nttdata.emeal.msvc.client.mapper;

import com.nttdata.emeal.msvc.client.model.Client;
import com.nttdata.emeal.msvc.client.model.EnterpriseClient;
import com.nttdata.emeal.msvc.client.model.PersonalClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import static com.nttdata.emeal.msvc.client.utils.Constants.ENTERPRISE;
import static com.nttdata.emeal.msvc.client.utils.Constants.PERSONAL;

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
    personalClient.setClientType(PERSONAL);
    return  personalClient;
  }

  public EnterpriseClient mapToEnterpriseClientFromClient(EnterpriseClient enterpriseClientDTO) {
    EnterpriseClient enterpriseClient = new EnterpriseClient();
    BeanUtils.copyProperties(enterpriseClientDTO, enterpriseClient);
    enterpriseClient.setClientType(ENTERPRISE);
    return  enterpriseClient;
  }

  public PersonalClient mapToPersonalClientFromClient(Client clientFounded, PersonalClient personalClientDTO) {
    PersonalClient personalClient = new PersonalClient();
    personalClient.setId(clientFounded.getId());
    personalClient.setDocumentNumber(clientFounded.getDocumentNumber());
    personalClient.setDocumentType(clientFounded.getDocumentType());
    personalClient.setPhone(clientFounded.getPhone());
    personalClient.setEmail(clientFounded.getEmail());
    personalClient.setNames(personalClientDTO.getNames());
    personalClient.setSurnames(personalClientDTO.getSurnames());
    personalClient.setClientType(PERSONAL);
    return personalClient;
  }

  public EnterpriseClient mapToEnterpriseClientFromClient(Client clientFounded, EnterpriseClient enterpriseClientDTO) {
    EnterpriseClient enterpriseClient = new EnterpriseClient();
    enterpriseClient.setId(clientFounded.getId());
    enterpriseClient.setDocumentNumber(clientFounded.getDocumentNumber());
    enterpriseClient.setDocumentType(clientFounded.getDocumentType());
    enterpriseClient.setPhone(clientFounded.getPhone());
    enterpriseClient.setEmail(clientFounded.getEmail());
    enterpriseClient.setBusinessName(enterpriseClientDTO.getBusinessName());
    enterpriseClient.setClientType(ENTERPRISE);
    return enterpriseClient;
  }


}
