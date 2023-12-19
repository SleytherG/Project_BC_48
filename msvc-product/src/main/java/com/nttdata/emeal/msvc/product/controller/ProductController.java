package com.nttdata.emeal.msvc.product.controller;

import com.nttdata.emeal.msvc.product.dto.*;
import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.service.ClientService;
import com.nttdata.emeal.msvc.product.service.DebtService;
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
  public static final String CLIENT_ID = "/{clientId}";
  public static final String CREATE_SAVINGS_ACCOUNT = "/createSavingsAccount";
  public static final String CREATE_CHECKING_ACCOUNT = "/createCheckingAccount";
  public static final String CREATE_FIXED_TERM_ACCOUNT = "/createFixedTermAccount";
  public static final String CREATE_LOAN_BANK = "/createLoanBank";
  public static final String CREATE_CREDIT_CARD = "/createCreditCard";
  public static final String UPDATE_SAVINGS_ACCOUNT = "/updateSavingsAccount";
  public static final String UPDATE_CHECKING_ACCOUNT = "/updateCheckingAccount";
  public static final String UPDATE_FIXED_TERM_ACCOUNT = "/updateFixedTermAccount";
  public static final String UPDATE_LOAN_BANK = "/updateLoanBank";
  public static final String UPDATE_CREDIT_CARD = "/updateCreditCard";
  public static final String GET_ALL_PRODUCTS_BY_CLIENT_ID = "/getAllProductsByClientId";
  public static final String DEPOSIT = "/deposit";
  public static final String WITHDRAWAL = "/withdrawal";
  public static final String GET_ALL_DEBTS_BY_CLIENT_ID = "/getAllDebtsByClientId";
  public static final String PAY_CREDIT_PRODUCT = "/payCreditProduct";
  public static final String CHARGE_CONSUMPTION_TO_CREDIT_CARD = "/chargeConsumptionToCreditCard";
  public static final String GET_ALL_TRANSACTIONS_BY_PRODUCT_ID = "/getAllTransactionsByProductId";
  public static final String CREATE_SAVINGS_ACCOUNT_VIP_CLIENT = "/createSavingsAccountVipClient";
  public static final String CREATE_CHECKING_ACCOUNT_PYME_CLIENT = "/createCheckingAccountPymeClient";
  public static final String BANK_TRANSFER_TO_OTHER_ACCOUNT = "/bankTransferToOtherAccount";
  public static final String GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID = "/getAllCommissionsCurrentMonthByProductId";
  public static final String ASSOCIATE_ACCOUNT_TO_DEBIT_CARD = "/associateAccountToDebitCard";
  public static final String PAY_WITH_DEBIT_CARD = "/payWithDebitCard";
  public static final String ESTABLISH_MAIN_ACCOUNT_TO_DEBIT_CARD = "/establishMainAccountToDebitCard";
  public static final String GET_LAST_TEN_TRANSACTIONS_OF_DEBIT_CARD_OR_CREDIT_CARD = "/getLastTenTransactionsOfDebitCardOrCreditCard";
  public static final String GET_BALANCE_OF_MAIN_ACCOUNT = "/getBalanceOfMainAccount";

  public static final String RETRIEVE_A_CLIENT = "/retrieveAClient";


  @Autowired
  private ProductService productService;

  @Autowired
  private ClientService clientService;

  @Autowired
  private DebtService debtService;

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

  @PutMapping(UPDATE_SAVINGS_ACCOUNT + PRODUCT_ID)
  public Single<SavingsAccount> updateSavingsAccount(@PathVariable String productId, @RequestBody SavingsAccount savingsAccount) {
    return productService.updateSavingsAccount(productId, savingsAccount);
  }

  @PutMapping(UPDATE_CHECKING_ACCOUNT + PRODUCT_ID)
  public Single<CheckingAccount> updateCheckingAccount(@PathVariable String productId, @RequestBody CheckingAccount checkingAccount) {
    return productService.updateCheckingAccount(productId, checkingAccount);
  }

  @PutMapping(UPDATE_FIXED_TERM_ACCOUNT +  PRODUCT_ID)
  public Single<FixedTermAccount> updateFixedTermAccount(@PathVariable String productId, @RequestBody FixedTermAccount fixedTermAccount) {
    return productService.updateFixedTermAccount(productId, fixedTermAccount);
  }

  @PutMapping(UPDATE_LOAN_BANK + PRODUCT_ID)
  public Single<LoanBank> updateLoanBank(@PathVariable String productId, @RequestBody LoanBank loanBank) {
    return productService.updateLoanBank(productId, loanBank);
  }

  @PutMapping( UPDATE_CREDIT_CARD + PRODUCT_ID)
  public Single<CreditCard> updateCreditCard(@PathVariable String productId, @RequestBody CreditCard creditCard) {
    return productService.updateCreditCard(productId, creditCard);
  }

  @DeleteMapping(PRODUCT_ID)
  public Completable deleteAProduct(@PathVariable String productId) {
    return productService.deleteAProduct(productId);
  }

  @GetMapping(GET_ALL_PRODUCTS_BY_CLIENT_ID + CLIENT_ID)
  public Flowable<Product> getAllProductsByClientId(@PathVariable String clientId) {
    return productService.getAllProductsByClientId(clientId);
  }

  @GetMapping(RETRIEVE_A_CLIENT + CLIENT_ID)
  public Maybe<Client> retrieveAClient(@PathVariable String clientId) {
    return clientService.retrieveAClient(clientId);
  }

  @PostMapping(DEPOSIT)
  public Completable deposit(@RequestBody Deposit deposit) {
    return productService.deposit(deposit);
  }

  @PostMapping(WITHDRAWAL)
  public Completable withdrawal(@RequestBody Withdrawal withdrawal) {
    return productService.withdrawal(withdrawal);
  }

  @GetMapping(GET_ALL_DEBTS_BY_CLIENT_ID + CLIENT_ID)
  public Flowable<Debt> getAllDebtsByClientId(@PathVariable String clientId) {
    return debtService.getAllDebtsByClientId(clientId);
  }

  @PostMapping(PAY_CREDIT_PRODUCT)
  public Completable payCreditProduct(@RequestBody PayCreditProductDTO payCreditProductDTO) {
    return productService.payCreditProduct(payCreditProductDTO);
  }

  @PostMapping(CHARGE_CONSUMPTION_TO_CREDIT_CARD)
  public Completable chargeConsumptionToCreditCard(@RequestBody ChargeConsumptionToCreditCardDTO chargeConsumptionToCreditCardDTO) {
    return productService.chargeConsumptionToCreditCard(chargeConsumptionToCreditCardDTO);
  }

  @GetMapping(GET_ALL_TRANSACTIONS_BY_PRODUCT_ID + PRODUCT_ID)
  public Flowable<Transaction> getAllTransactionsByProductId(@PathVariable String productId) {
    return productService.getAllTransactionsByProductId(productId);
  }

  @PostMapping(CREATE_SAVINGS_ACCOUNT_VIP_CLIENT)
  public Single<SavingsAccount> createSavingsAccountVipClient(@RequestBody SavingsAccountVipClientDTO savingsAccountVipClientDTO) {
    return productService.createSavingsAccountVipClient(savingsAccountVipClientDTO);
  }

  @PostMapping(CREATE_CHECKING_ACCOUNT_PYME_CLIENT)
  public Single<CheckingAccount> createCheckingAccountPymeClient(@RequestBody CheckingAccountPymeClientDTO checkingAccountPymeClientDTO) {
    return productService.createCheckingAccountPymeClient(checkingAccountPymeClientDTO);
  }

  @PostMapping(BANK_TRANSFER_TO_OTHER_ACCOUNT)
  public Completable bankTransferToOtherAccount(@RequestBody BankTransferToOtherAccountDTO bankTransferToOtherAccountDTO) {
    return productService.bankTransferToOtherAccount(bankTransferToOtherAccountDTO);
  }

  @GetMapping(GET_ALL_COMMISSIONS_CURRENT_MONTH_BY_PRODUCT_ID + PRODUCT_ID)
  public Flowable<Commission> getAllCommissionsCurrentMonthByProductId(@PathVariable String productId) {
    return productService.getAllCommissionsCurrentMonthByProductId(productId);
  }

  @PostMapping(ASSOCIATE_ACCOUNT_TO_DEBIT_CARD)
  public Single<DebitCard> associateAccountToDebitCard(@RequestBody AssociateAccountToDebitCardDTO associateAccountToDebitCardDTO) {
    return productService.associateAccountToDebitCard(associateAccountToDebitCardDTO);
  }

  @PostMapping(PAY_WITH_DEBIT_CARD)
  public Completable payWithDebitCard(@RequestBody PayWithDebitCardDTO payWithDebitCardDTO) {
    return productService.payWithDebitCard(payWithDebitCardDTO);
  }

  @PostMapping(ESTABLISH_MAIN_ACCOUNT_TO_DEBIT_CARD)
  public Single<DebitCard> establishMainAccountToDebitCard(@RequestBody EstablishMainAccountToDebitCardDTO establishMainAccountToDebitCardDTO) {
    return productService.establishMainAccountToDebitCard(establishMainAccountToDebitCardDTO);
  }

  @GetMapping(GET_LAST_TEN_TRANSACTIONS_OF_DEBIT_CARD_OR_CREDIT_CARD)
  public Flowable<Transaction> getLastTenTransactionsOfDebitCardOrCreditCard(@RequestBody Product product) {
    return productService.getLastTenTransactionsOfDebitCardOrCreditCard(product.getId());
  }

  @GetMapping(GET_BALANCE_OF_MAIN_ACCOUNT + PRODUCT_ID)
  public Single<String> getBalanceOfMainAccount(@PathVariable String productId) {
    return productService.getBalanceOfMainAccount(productId);
  }













}
