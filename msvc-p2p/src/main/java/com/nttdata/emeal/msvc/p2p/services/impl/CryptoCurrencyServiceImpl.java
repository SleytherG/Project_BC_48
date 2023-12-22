package com.nttdata.emeal.msvc.p2p.services.impl;

import com.nttdata.emeal.msvc.p2p.dto.CryptoCurrencyDTO;
import com.nttdata.emeal.msvc.p2p.exceptions.CryptoCurrencyNotFoundException;
import com.nttdata.emeal.msvc.p2p.mapper.CryptoCurrencyMapper;
import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import com.nttdata.emeal.msvc.p2p.repository.CryptoCurrencyRepository;
import com.nttdata.emeal.msvc.p2p.services.CryptoCurrencyService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

  @Autowired
  private CryptoCurrencyRepository cryptoCurrencyRepository;

  @Autowired
  private CryptoCurrencyMapper cryptoCurrencyMapper;

  @Override
  public Flowable<CryptoCurrency> retrieveAllCryptoCurrencies() {
    return cryptoCurrencyRepository.findAll();
  }

  @Override
  public Maybe<CryptoCurrency> retrieveACryptoCurrency(String cryptoCurrencyId) {
    return cryptoCurrencyRepository
      .findById(cryptoCurrencyId)
      .switchIfEmpty(Maybe.error(new CryptoCurrencyNotFoundException("CryptoCurrency not found with id: " + cryptoCurrencyId)));
  }

  @Override
  public Single<CryptoCurrency> saveACryptoCurrency(CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyRepository
      .save(cryptoCurrencyMapper.mapFromCryptoCurrencyDTOToCryptoCurrency(cryptoCurrencyDTO));
  }

  @Override
  public Single<CryptoCurrency> updateACryptoCurrency(String cryptoCurrencyId, CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyRepository
      .findById(cryptoCurrencyId)
      .switchIfEmpty(Single.error(new CryptoCurrencyNotFoundException("CryptoCurrency not found with id: " + cryptoCurrencyId)))
      .flatMap(cryptoCurrency -> cryptoCurrencyRepository.save(cryptoCurrencyMapper.mapFromCryptoCurrencyToCryptoCurrencyDTO(cryptoCurrency, cryptoCurrencyDTO)));
  }

  @Override
  public Completable deleteACryptoCurrency(String cryptoCurrencyId) {
    return cryptoCurrencyRepository
      .findById(cryptoCurrencyId)
      .switchIfEmpty(Maybe.error(new CryptoCurrencyNotFoundException("CryptoCurrency not found with id: " + cryptoCurrencyId)))
      .flatMapCompletable(cryptoCurrency -> Completable.fromAction(() -> cryptoCurrencyRepository.deleteById(cryptoCurrency.getId()).subscribe()));
  }

  @Override
  public Single<CryptoCurrency> setCurrentRate(CryptoCurrencyDTO cryptoCurrencyDTO) {
    return cryptoCurrencyRepository
      .findById(cryptoCurrencyDTO.getId())
      .switchIfEmpty(Single.error(new CryptoCurrencyNotFoundException("CryptoCurrency not found with id: " + cryptoCurrencyDTO.getId())))
      .flatMap(cryptoCurrency -> {
        cryptoCurrency.setPurchaseRate(cryptoCurrencyDTO.getPurchaseRate());
        cryptoCurrency.setSalesRate(cryptoCurrencyDTO.getSalesRate());
        return cryptoCurrencyRepository.save(cryptoCurrency);
      });
  }
}
