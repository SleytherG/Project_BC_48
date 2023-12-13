package com.nttdata.emeal.msvc.product.controller;

import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.service.ProductService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductController.PRODUCTS)
public class ProductController {

  public static final String PRODUCTS = "/products";
  public static final String PRODUCT_ID = "/{productId}";
  public static final String CREATE_SAVINGS_ACCOUNT = "/createSavingsAccount";
  public static final String CREATE_CHECKING_ACCOUNT = "/createCheckingAccount";
  public static final String CREATE_FIXED_TERM_ACCOUNT = "/createFixedTermAccount";
  public static final String CREATE_LOAN_BANK = "/createLoanBank";
  public static final String CREATE_CREDIT_CARD = "/createCreditCard";

  @Autowired
  private ProductService productService;

  @GetMapping(produces = {"application/json"})
  public Flowable<Product> retrieveAllProducts() {
    return productService.retrieveAllProducts();
  }

  @GetMapping(PRODUCT_ID)
  public Maybe<Product> retrieveAProduct(@PathVariable String productId) {
    return productService.retrieveAProduct(productId);
  }

  @PostMapping(value = CREATE_SAVINGS_ACCOUNT , produces = {"application/json"})
  public Single<SavingsAccount> createSavingsAccount(@RequestBody SavingsAccount savingsAccount) {
   return productService.createSavingsAccount(savingsAccount);
  }

  @PostMapping(value = CREATE_CHECKING_ACCOUNT,produces = {"application/json"})
  public Single<CheckingAccount> createCheckingAccount(@RequestBody CheckingAccount checkingAccount) {
    return productService.createCheckingAccount(checkingAccount);
  }

  @PostMapping(value = CREATE_FIXED_TERM_ACCOUNT, produces = {"application/json"})
  public Single<FixedTermAccount> createFixedTermAccount(@RequestBody FixedTermAccount fixedTermAccount) {
    return productService.createFixedTermAccount(fixedTermAccount);
  }

  @PostMapping(value = CREATE_LOAN_BANK, produces = {"application/json"})
  public Single<LoanBank> createLoanBank(@RequestBody LoanBank loanBank) {
    return productService.createLoanBank(loanBank);
  }

  @PostMapping(value = CREATE_CREDIT_CARD, produces = {"application/json"})
  public Single<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
    return productService.createCreditCard(creditCard);
  }

  @PutMapping(PRODUCT_ID)
  public Single<SavingsAccount> updateSavingsAccount(@PathVariable String productId, @RequestBody SavingsAccount savingsAccount) {
    return productService.updateSavingsAccount(productId, savingsAccount);
  }

  @PutMapping(PRODUCT_ID)
  public Single<CheckingAccount> updateCheckingAccount(@PathVariable String productId, @RequestBody CheckingAccount checkingAccount) {
    return productService.updateCheckingAccount(productId, checkingAccount);
  }

  @PutMapping(PRODUCT_ID)
  public Single<FixedTermAccount> updateFixedTermAccount(@PathVariable String productId, @RequestBody FixedTermAccount fixedTermAccount) {
    return productService.updateFixedTermAccount(productId, fixedTermAccount);
  }

  @PutMapping(PRODUCT_ID)
  public Single<LoanBank> updateLoanBank(@PathVariable String productId, @RequestBody LoanBank loanBank) {
    return productService.updateLoanBank(productId, loanBank);
  }

  @PutMapping(PRODUCT_ID)
  public Single<CreditCard> updateCreditCard(@PathVariable String productId, @RequestBody CreditCard creditCard) {
    return productService.updateCreditCard(productId, creditCard);
  }

  @DeleteMapping(PRODUCT_ID)
  public Completable deleteAProduct(@PathVariable String productId) {
    return productService.deleteAProduct(productId);
  }



}
