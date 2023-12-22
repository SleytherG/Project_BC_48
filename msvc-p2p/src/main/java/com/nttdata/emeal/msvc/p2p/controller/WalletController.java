package com.nttdata.emeal.msvc.p2p.controller;

import com.nttdata.emeal.msvc.p2p.dto.WalletDTO;
import com.nttdata.emeal.msvc.p2p.model.Wallet;
import com.nttdata.emeal.msvc.p2p.services.WalletService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WalletController.P2P + WalletController.WALLETS)
public class WalletController {

  public static final String P2P = "/p2p";
  public static final String WALLETS = "/wallets";
  public static final String WALLET_ID = "/{walletId}";

  @Autowired
  private WalletService walletService;

  @GetMapping()
  public Flowable<Wallet> retrieveAllWallets() {
    return walletService.retrieveAllWallets();
  }

  @GetMapping( WALLET_ID)
  public Maybe<Wallet> retrieveAWallet(@PathVariable String walletId) {
    return walletService.retrieveAWallet(walletId);
  }

  @PostMapping()
  public Single<Wallet> saveAWallet(@RequestBody WalletDTO walletDTO) {
    return walletService.saveAWallet(walletDTO);
  }

  @PutMapping(WALLET_ID)
  public Single<Wallet> updateAWallet(@PathVariable String walletId, @RequestBody WalletDTO walletDTO) {
    return walletService.updateAWallet(walletId, walletDTO);
  }

  @DeleteMapping(WALLET_ID)
  public Completable deleteAWallet(@PathVariable String walletId) {
    return walletService.deleteAWallet(walletId);
  }
}
