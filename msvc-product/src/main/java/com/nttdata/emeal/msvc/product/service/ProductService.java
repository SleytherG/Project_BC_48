package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.dto.*;
import com.nttdata.emeal.msvc.product.model.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
  Flowable<Product> retrieveAllProducts();

  Maybe<Product> retrieveAProduct(String productId);

  @Transactional
  Single<SavingsAccount> createSavingsAccount(ProductDTO productDTO);

  @Transactional
  Single<CheckingAccount> createCheckingAccount(ProductDTO productDTO);

  @Transactional
  Single<FixedTermAccount> createFixedTermAccount(ProductDTO productDTO);

  @Transactional
  Single<LoanBank> createLoanBank(LoanBank loanBank);

  @Transactional
  Single<CreditCard> createCreditCard(CreditCard creditCard);

  @Transactional
  Single<SavingsAccount> updateSavingsAccount(String productId, SavingsAccount savingsAccount);

  @Transactional
  Single<CheckingAccount> updateCheckingAccount(String productId, CheckingAccount checkingAccount);

  @Transactional
  Single<FixedTermAccount> updateFixedTermAccount(String productId, FixedTermAccount fixedTermAccount);

  @Transactional
  Single<LoanBank> updateLoanBank(String productId, LoanBank loanBank);

  @Transactional
  Single<CreditCard> updateCreditCard(String productId, CreditCard creditCard);

  Completable deleteAProduct(String productId);

  Flowable<ProductDTO> getAllProductsByClientId(String clientId);

  Completable deposit(Deposit deposit);

  Completable withdrawal(Withdrawal withdrawal);

  Completable payCreditProduct(PayCreditProductDTO payCreditProductDTO);

  Completable chargeConsumptionToCreditCard(ChargeConsumptionToCreditCardDTO chargeConsumptionToCreditCardDTO);

  Flowable<Transaction> getAllTransactionsByProductId(String productId);

  Single<SavingsAccount> createSavingsAccountVipClient(SavingsAccountVipClientDTO savingsAccountVipClientDTO);

  Single<CheckingAccount> createCheckingAccountPymeClient(CheckingAccountPymeClientDTO checkingAccountPymeClientDTO);

  Completable bankTransferToOtherAccount(BankTransferToOtherAccountDTO bankTransferToOtherAccountDTO);

  Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId);

  Single<DebitCard> associateAccountToDebitCard(AssociateAccountToDebitCardDTO associateAccountToDebitCardDTO);

  Completable payWithDebitCard(PayWithDebitCardDTO payWithDebitCardDTO);

  Single<DebitCard> establishMainAccountToDebitCard(EstablishMainAccountToDebitCardDTO establishMainAccountToDebitCardDTO);

  Flowable<Transaction> getLastTenTransactionsOfDebitCardOrCreditCard(String id);

  Single<String> getBalanceOfMainAccount(String productId);
}
