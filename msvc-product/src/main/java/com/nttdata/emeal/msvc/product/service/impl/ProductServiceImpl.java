package com.nttdata.emeal.msvc.product.service.impl;

import com.nttdata.emeal.msvc.product.dto.ChargeConsumptionToCreditCardDTO;
import com.nttdata.emeal.msvc.product.dto.PayCreditProductDTO;
import com.nttdata.emeal.msvc.product.dto.ProductDTO;
import com.nttdata.emeal.msvc.product.exceptions.NotEnoughCreditLineException;
import com.nttdata.emeal.msvc.product.exceptions.ProductNotFoundException;
import com.nttdata.emeal.msvc.product.mapper.ProductMapper;
import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.repository.ProductRepository;
import com.nttdata.emeal.msvc.product.service.ClientService;
import com.nttdata.emeal.msvc.product.service.DebtService;
import com.nttdata.emeal.msvc.product.service.ProductService;
import com.nttdata.emeal.msvc.product.service.TransactionService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.nttdata.emeal.msvc.product.utils.Constants.*;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ClientService clientService;

  @Autowired
  private DebtService debtService;

  @Autowired
  private TransactionService transactionService;

  @Override
  public Flowable<Product> retrieveAllProducts() {
    return productRepository
      .findAll();
  }

  @Override
  public Maybe<Product> retrieveAProduct(String productId) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Maybe.error(new NotFoundException("Product not found with id: " + productId)));
  }

  @Override
  @Transactional
  public Single<SavingsAccount> createSavingsAccount(SavingsAccount savingsAccount) {
    return productRepository
      .getProductsByIdClient(savingsAccount.getIdClient())
      .toList()
      .observeOn(Schedulers.io())
      .flatMap(products -> getSavingsAccountSingle(savingsAccount, products));
  }

  private Single<SavingsAccount> getSavingsAccountSingle(SavingsAccount savingsAccount, List<Product> products) {
    Client client = clientService.retrieveAClient(savingsAccount.getIdClient()).blockingGet();
    if ( products.isEmpty() &&
      !Objects.equals(Objects.requireNonNull(client).getClientType(), ENTERPRISE) &&
      Objects.requireNonNull(client).getClientType().equals(PERSONAL)
    ) {
      return productMapper.mapSavingsAccountAndSave(savingsAccount);
    } else {
      return Single.error(new Throwable("The client already has an equal product"));
    }
  }

  @Override
  @Transactional
  public Single<CheckingAccount> createCheckingAccount(CheckingAccount checkingAccount) {
    return productRepository
      .getProductsByIdClient(checkingAccount.getIdClient())
      .toList()
      .flatMap(products -> getCheckingAccountSingle(checkingAccount, products));
  }

  private Single<CheckingAccount> getCheckingAccountSingle(CheckingAccount checkingAccount, List<Product> products) {
    Client client = clientService.retrieveAClient(checkingAccount.getIdClient()).blockingGet();
    if ( products.isEmpty() && Objects.requireNonNull(client).getClientType().equals(PERSONAL) ) {
      return productMapper.mapCheckingAccountAndSave(checkingAccount);
    } else if ( Objects.requireNonNull(client).getClientType().equals(ENTERPRISE) ) {
      return productMapper.mapCheckingAccountAndSave(checkingAccount);
    } else {
      return Single.error(new Throwable("The client already has an equal product"));
    }
  }

  @Override
  @Transactional
  public Single<FixedTermAccount> createFixedTermAccount(FixedTermAccount fixedTermAccount) {
    return productRepository
      .getProductsByIdClient(fixedTermAccount.getIdClient())
      .toList()
      .flatMap(products -> {
        if ( products.isEmpty() ) {
          return productMapper.mapFixedTermAccountAndSave(fixedTermAccount);
        } else {
          return Single.error(new Throwable("The client already has an equal product"));
        }
      });
  }

  @Override
  @Transactional
  public Single<LoanBank> createLoanBank(LoanBank loanBank) {
    return productMapper.mapLoanBankAndSave(loanBank);
  }

  @Override
  @Transactional
  public Single<CreditCard> createCreditCard(CreditCard creditCard) {
    return productMapper.mapCreditCardAndSave(creditCard);
  }

  @Override
  @Transactional
  public Single<SavingsAccount> updateSavingsAccount(String productId, SavingsAccount savingsAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Savings account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapSavingsAccountAndUpdate(productFounded, savingsAccount));
  }

  @Override
  @Transactional
  public Single<CheckingAccount> updateCheckingAccount(String productId, CheckingAccount checkingAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Checking account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCheckingAccountAndUpdate(productFounded, checkingAccount));
  }

  @Override
  @Transactional
  public Single<FixedTermAccount> updateFixedTermAccount(String productId, FixedTermAccount fixedTermAccount) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Fixed term account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapFixedTermAccountAndUpdate(productFounded, fixedTermAccount));
  }

  @Override
  @Transactional
  public Single<LoanBank> updateLoanBank(String productId, LoanBank loanBank) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Loan bank not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapLoanBankAndUpdate(productFounded, loanBank));
  }

  @Override
  @Transactional
  public Single<CreditCard> updateCreditCard(String productId, CreditCard creditCard) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Credit card not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCreditCardAndUpdate(productFounded, creditCard));
  }

  @Override
  public Completable deleteAProduct(String productId) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Maybe.error(new ProductNotFoundException("Product not found with id: " + productId)))
      .flatMapCompletable(product -> Completable.fromAction(() -> productRepository.deleteById(productId).subscribe()))
      .doOnComplete(() -> log.debug("Product deleted"));
  }

  @Override
  public Flowable<Product> getAllProductsByClientId(String clientId) {
    return productRepository
      .getProductsByIdClient(clientId)
      .switchIfEmpty(Flowable.error(new ProductNotFoundException("The client currently has no products")));
  }

  @Override
  public Completable deposit(Deposit deposit) {
    return Completable.fromAction(() -> {
      BankAccount sourceProduct = (BankAccount) productRepository.findById(deposit.getSourceProductId()).blockingGet();
      BankAccount targetProduct = (BankAccount) productRepository.findById(deposit.getTargetProductId()).blockingGet();
      sourceProduct.deposit(deposit.getAmount());
      sourceProduct.withdrawal(deposit.getAmount());
      targetProduct.deposit(deposit.getAmount());
      productRepository.save(sourceProduct).subscribe();
      productRepository.save(targetProduct).subscribe();
    });
  }

  @Override
  public Completable withdrawal(Withdrawal withdrawal) {
    return productRepository.findProductById(withdrawal.getSourceProductId())
      .flatMapCompletable( sourceProduct -> {
        BankAccount bankAccount = (BankAccount) sourceProduct;
        bankAccount.withdrawal(withdrawal.getAmount());
        productRepository.save(bankAccount).subscribe();
        return Completable.complete();
      });
  }

  @Override
  public Completable payCreditProduct(PayCreditProductDTO payCreditProductDTO) {
    return productRepository
      .findById(payCreditProductDTO.getProductId())
      .switchIfEmpty(Maybe.error(new ProductNotFoundException("Product not found with id: " + payCreditProductDTO.getProductId())))
      .flatMapCompletable(product -> {
        Flowable<Debt> debtsByProductId = debtService.getAllDebtsByProductId(product.getId());
        debtsByProductId
          .filter(debt -> debt.getIdProduct().equals(product.getId()))
          .subscribe(debt -> {
           debt.setAmount(debt.getAmount().subtract(payCreditProductDTO.getAmount()));
           debtService.updateDebt(debt.getId(), debt).subscribe();
          }).dispose();
        return Completable.complete();
      });
  }

  @Override
  public Completable chargeConsumptionToCreditCard(ChargeConsumptionToCreditCardDTO chargeConsumptionToCreditCardDTO) {
    return productRepository
      .findProductById(chargeConsumptionToCreditCardDTO.getProductId())
      .flatMapCompletable( product -> {
        CreditCard creditCard = (CreditCard) product;
        BigDecimal substractResult = creditCard.getCurrentLine().subtract(chargeConsumptionToCreditCardDTO.getCharge());
        if ( substractResult.compareTo(BigDecimal.ZERO) < 0 ) {
          throw new NotEnoughCreditLineException("Your credit card does not have enough balance to charge a new consumption.");
        } else {
          creditCard.setCurrentLine(substractResult);
          productRepository.save(creditCard).subscribe();
        }
        return Completable.complete();
      });
  }

  @Override
  public Flowable<Transaction> getAllTransactionsByProductId(String productId) {
    return transactionService.getAllTransactionsByProductId(productId);
  }

}
