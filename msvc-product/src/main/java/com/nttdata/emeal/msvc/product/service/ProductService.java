package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface ProductService {
  Flowable<Product> retrieveAllProducts();

  Maybe<Product> retrieveAProduct(String productId);

  Single<SavingsAccount> createSavingsAccount(SavingsAccount savingsAccount);

  Single<CheckingAccount> createCheckingAccount(CheckingAccount checkingAccount);

  Single<FixedTermAccount> createFixedTermAccount(FixedTermAccount fixedTermAccount);

  Single<LoanBank> createLoanBank(LoanBank loanBank);

  Single<CreditCard> createCreditCard(CreditCard creditCard);

  Single<SavingsAccount> updateSavingsAccount(String productId, SavingsAccount savingsAccount);

  Single<CheckingAccount> updateCheckingAccount(String productId, CheckingAccount checkingAccount);

  Single<FixedTermAccount> updateFixedTermAccount(String productId, FixedTermAccount fixedTermAccount);

  Single<LoanBank> updateLoanBank(String productId, LoanBank loanBank);

  Single<CreditCard> updateCreditCard(String productId, CreditCard creditCard);

  Completable deleteAProduct(String productId);
}
