package com.nttdata.emeal.msvc.p2p.repository;

import com.nttdata.emeal.msvc.p2p.model.CryptoCurrency;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface CryptoCurrencyRepository extends RxJava3CrudRepository<CryptoCurrency, String> {
}
