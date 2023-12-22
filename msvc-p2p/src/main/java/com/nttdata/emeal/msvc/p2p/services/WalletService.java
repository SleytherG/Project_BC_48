package com.nttdata.emeal.msvc.p2p.services;

import com.nttdata.emeal.msvc.p2p.dto.WalletDTO;
import com.nttdata.emeal.msvc.p2p.model.Wallet;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface WalletService {
  Flowable<Wallet> retrieveAllWallets();

  Maybe<Wallet> retrieveAWallet(String walletId);

  Single<Wallet> saveAWallet(WalletDTO walletDTO);

  Single<Wallet> updateAWallet(String walletId, WalletDTO walletDTO);

  Completable deleteAWallet(String walletId);
}
