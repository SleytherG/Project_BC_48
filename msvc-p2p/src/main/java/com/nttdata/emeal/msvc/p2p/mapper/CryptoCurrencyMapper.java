package com.nttdata.emeal.msvc.p2p.mapper;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyMapper {

  public CryptoCurrency mapFromCryptoCurrencyDTOToCryptoCurrency(CryptoCurrencyDTO cryptoCurrencyDTO) {
    return CryptoCurrency
      .builder()
      .name(cryptoCurrencyDTO.getName())
      .purchaseRate(cryptoCurrencyDTO.getPurchaseRate())
      .salesRate(cryptoCurrencyDTO.getSalesRate())
      .build();
  }

  public CryptoCurrency mapFromCryptoCurrencyToCryptoCurrencyDTO(CryptoCurrency cryptoCurrency, CryptoCurrencyDTO cryptoCurrencyDTO) {
    BeanUtils.copyProperties(cryptoCurrencyDTO, cryptoCurrency, "id");
    return cryptoCurrency;
  }
}
