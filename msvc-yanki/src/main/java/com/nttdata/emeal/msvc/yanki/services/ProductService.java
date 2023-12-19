package com.nttdata.emeal.msvc.yanki.services;

import com.nttdata.emeal.msvc.yanki.model.BankAccount;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface ProductService {

  public Flowable<BankAccount> getProductsByClientId(String clientId);
}
