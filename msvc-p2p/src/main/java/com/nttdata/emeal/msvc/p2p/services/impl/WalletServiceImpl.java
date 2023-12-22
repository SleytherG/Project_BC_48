package com.nttdata.emeal.msvc.p2p.services.impl;

import com.nttdata.emeal.msvc.p2p.dto.WalletDTO;
import com.nttdata.emeal.msvc.p2p.exceptions.WalletNotFoundException;
import com.nttdata.emeal.msvc.p2p.mapper.WalletMapper;
import com.nttdata.emeal.msvc.p2p.model.Wallet;
import com.nttdata.emeal.msvc.p2p.repository.WalletRepository;
import com.nttdata.emeal.msvc.p2p.services.WalletService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

  @Autowired
  private WalletRepository walletRepository;

  @Autowired
  private WalletMapper walletMapper;

  @Override
  public Flowable<Wallet> retrieveAllWallets() {
    return walletRepository.findAll();
  }

  @Override
  public Maybe<Wallet> retrieveAWallet(String walletId) {
    return walletRepository
      .findById(walletId)
      .switchIfEmpty(Maybe.error(new WalletNotFoundException("Wallet not found with id: " + walletId)));
  }

  @Override
  public Single<Wallet> saveAWallet(WalletDTO walletDTO) {
    return walletRepository
      .save(walletMapper.mapFromWalletDTOToWallet(walletDTO));
  }

  @Override
  public Single<Wallet> updateAWallet(String walletId, WalletDTO walletDTO) {
    return walletRepository
      .findById(walletId)
      .switchIfEmpty(Single.error(new WalletNotFoundException("Wallet not found with id: " + walletId)))
      .flatMap(wallet -> walletRepository.save(walletMapper.mapFromWalletDTOToWalletToSave(wallet, walletDTO)));
  }

  @Override
  public Completable deleteAWallet(String walletId) {
    return walletRepository
      .findById(walletId)
      .switchIfEmpty(Maybe.error(new WalletNotFoundException("Wallet not found with id: " + walletId)))
      .flatMapCompletable(wallet -> Completable.fromAction(() -> walletRepository.deleteById(wallet.getId()).subscribe()));
  }
}
