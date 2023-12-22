package com.nttdata.emeal.msvc.p2p.services;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyDTO;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface CryptoCurrencyService {
  Flowable<CryptoCurrency> retrieveAllCryptoCurrencies();

  Maybe<CryptoCurrency> retrieveACryptoCurrency(String cryptoCurrencyId);

  Single<CryptoCurrency> saveACryptoCurrency(CryptoCurrencyDTO cryptoCurrencyDTO);

  Single<CryptoCurrency> updateACryptoCurrency(String cryptoCurrencyId, CryptoCurrencyDTO cryptoCurrencyDTO);

  Completable deleteACryptoCurrency(String cryptoCurrencyId);

  Single<CryptoCurrency> setCurrentRate(CryptoCurrencyDTO cryptoCurrencyDTO);
}
