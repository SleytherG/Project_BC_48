package com.nttdata.emeal.msvc.product.service.impl;

import com.nttdata.emeal.msvc.product.exceptions.ProductNotFoundException;
import com.nttdata.emeal.msvc.product.mapper.ProductMapper;
import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.repository.ProductRepository;
import com.nttdata.emeal.msvc.product.service.ProductService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;

  @Override
  public Flowable<Product> retrieveAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Maybe<Product> retrieveAProduct(String productId) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Maybe.error(new NotFoundException("Product not found with id: " + productId)));
  }

  @Override
  public Single<SavingsAccount> createSavingsAccount(SavingsAccount savingsAccount) {
    return productRepository.save(savingsAccount);
  }

  @Override
  public Single<CheckingAccount> createCheckingAccount(CheckingAccount checkingAccount) {
    return productRepository.save(checkingAccount);
  }

  @Override
  public Single<FixedTermAccount> createFixedTermAccount(FixedTermAccount fixedTermAccount) {
    return productRepository.save(fixedTermAccount);
  }

  @Override
  public Single<LoanBank> createLoanBank(LoanBank loanBank) {
    return productRepository.save(loanBank);
  }

  @Override
  public Single<CreditCard> createCreditCard(CreditCard creditCard) {
    return productRepository.save(creditCard);
  }

  @Override
  public Single<SavingsAccount> updateSavingsAccount(String productId, SavingsAccount savingsAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Savings account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapSavingsAccountAndSave(productFounded, savingsAccount));
  }

  @Override
  public Single<CheckingAccount> updateCheckingAccount(String productId, CheckingAccount checkingAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Checking account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCheckingAccountAndSave(productFounded, checkingAccount));
  }

  @Override
  public Single<FixedTermAccount> updateFixedTermAccount(String productId, FixedTermAccount fixedTermAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Fixed term account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapFixedTermAccountAndSave(productFounded, fixedTermAccount));
  }

  @Override
  public Single<LoanBank> updateLoanBank(String productId, LoanBank loanBank) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Loan bank not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapLoanBankAndSave(productFounded, loanBank));
  }

  @Override
  public Single<CreditCard> updateCreditCard(String productId, CreditCard creditCard) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Credit card not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCreditCardAndSave(productFounded, creditCard));
  }

  @Override
  public Completable deleteAProduct(String productId) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Maybe.error(new ProductNotFoundException("Product not found with id: " + productId)))
      .flatMapCompletable(product -> Completable.fromAction(() -> productRepository.deleteById(productId).subscribe()))
      .doOnComplete(() -> log.debug("Product deleted"));
  }
}
