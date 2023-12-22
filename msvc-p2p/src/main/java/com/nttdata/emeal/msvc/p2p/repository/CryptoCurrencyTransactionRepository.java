package com.nttdata.emeal.msvc.p2p.repository;

import com.nttdata.emeal.msvc.p2p.model.CryptoCurrencyTransaction;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface CryptoCurrencyTransactionRepository extends RxJava3CrudRepository<CryptoCurrencyTransaction, String> {
}
