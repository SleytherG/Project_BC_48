package com.nttdata.emeal.msvc.p2p.repository;

import com.nttdata.emeal.msvc.p2p.model.Wallet;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface WalletRepository extends RxJava3CrudRepository<Wallet, String> {
}
