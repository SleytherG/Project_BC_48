package com.nttdata.emeal.msvc.p2p.mapper;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyTransactionDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrencyTransaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyTransactionMapper {

  public CryptoCurrencyTransaction mapFromCryptoCurrencyTransactionDTOToCryptoCurrencyTransaction(CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    return CryptoCurrencyTransaction
      .builder()
      .amount(cryptoCurrencyTransactionDTO.getAmount())
      .paymentMode(cryptoCurrencyTransactionDTO.getPaymentMode())
      .transactionNumber(cryptoCurrencyTransactionDTO.getTransactionNumber())
      .transactionDate(cryptoCurrencyTransactionDTO.getTransactionDate())
      .build();
  }

  public CryptoCurrencyTransaction mapFromCryptoCurrencyTransactionToSave(CryptoCurrencyTransaction cryptoCurrencyTransaction, CryptoCurrencyTransactionDTO cryptoCurrencyTransactionDTO) {
    BeanUtils.copyProperties(cryptoCurrencyTransactionDTO, cryptoCurrencyTransaction, "id");
    return cryptoCurrencyTransaction;
  }


}
