package com.nttdata.emeal.msvc.p2p.mapper;

import com.nttdata.emeal.msvc.p2p.dto.WalletDTO;
import com.nttdata.emeal.msvc.p2p.model.Wallet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

  public Wallet mapFromWalletDTOToWallet(WalletDTO walletDTO) {
    return Wallet
      .builder()
      .documentNumber(walletDTO.getDocumentNumber())
      .documentType(walletDTO.getDocumentType())
      .phoneNumber(walletDTO.getPhoneNumber())
      .email(walletDTO.getEmail())
      .cryptocurrencies(walletDTO.getCryptocurrencies())
      .build();
  }

  public Wallet mapFromWalletDTOToWalletToSave(Wallet wallet, WalletDTO walletDTO) {
    BeanUtils.copyProperties(walletDTO, wallet, "id");
    return wallet;
  }
}
