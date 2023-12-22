package com.nttdata.emeal.msvc.yanki.mapper;

import com.nttdata.emeal.msvc.yanki.dto.YankiDTO;
import com.nttdata.emeal.msvc.yanki.model.Yanki;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class YankiMapper {

  public Yanki mapYankiDTOToYanki(YankiDTO yankiDTO) {
    return Yanki.builder()
      .documentNumber(yankiDTO.getDocumentNumber())
      .documentType(yankiDTO.getDocumentType())
      .phoneNumber(yankiDTO.getPhoneNumber())
      .phoneImei(yankiDTO.getPhoneImei())
      .email(yankiDTO.getEmail())
      .balance(yankiDTO.getBalance())
      .associatedAccount(yankiDTO.getAssociatedAccount())
      .build();
  }

  public Yanki mapYankiDTOAndUpdate(Yanki yankiFounded, YankiDTO yankiDTO) {
    BeanUtils.copyProperties(yankiDTO, yankiFounded, "id");
    return yankiFounded;
  }

}
