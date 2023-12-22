package com.nttdata.emeal.msvc.p2p.controller;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import com.nttdata.emeal.msvc.p2p.services.CryptoCurrencyService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CryptoCurrencyController.P2P + CryptoCurrencyController.CRYPTOCURRENCIES)
public class CryptoCurrencyController {

  public static final String P2P = "/p2p";
  public static final String CRYPTOCURRENCIES = "/cryptoCurrencies";
  public static final String CRYPTOCURRENCY_ID = "/{cryptoCurrencyId}";
  public static final String SET_CURRENT_RATE = "/setCurrentRate";


  @Autowired
  private CryptoCurrencyService cryptoCurrencyService;


  @GetMapping()
  public Flowable<CryptoCurrency> retrieveAllCryptoCurrencies() {
    return cryptoCurrencyService.retrieveAllCryptoCurrencies();
  }

  @GetMapping( CRYPTOCURRENCY_ID)
  public Maybe<CryptoCurrency> retrieveACryptoCurrency(@PathVariable String cryptoCurrencyId) {
    return cryptoCurrencyService.retrieveACryptoCurrency(cryptoCurrencyId);
  }

  @PostMapping()
  public Single<CryptoCurrency> saveACryptoCurrency(@RequestBody CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyService.saveACryptoCurrency(cryptoCurrencyDTO);
  }

  @PutMapping( CRYPTOCURRENCY_ID)
  public Single<CryptoCurrency> updateACryptoCurrency(@PathVariable String cryptoCurrencyId, @RequestBody CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyService.updateACryptoCurrency(cryptoCurrencyId, cryptoCurrencyDTO);
  }

  @DeleteMapping( CRYPTOCURRENCY_ID)
  public Completable deleteACryptoCurrency(@PathVariable String cryptoCurrencyId) {
    return cryptoCurrencyService.deleteACryptoCurrency(cryptoCurrencyId);
  }

  @PostMapping(SET_CURRENT_RATE)
  public Single<CryptoCurrency> setCurrentRate(@RequestBody CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyService.setCurrentRate(cryptoCurrencyDTO);
  }


}
