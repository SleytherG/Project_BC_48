package com.nttdata.emeal.msvc.product.service.impl;

import com.nttdata.emeal.msvc.product.dto.*;
import com.nttdata.emeal.msvc.product.exceptions.ExpiredDebtExpection;
import com.nttdata.emeal.msvc.product.exceptions.NotEnoughCreditLineException;
import com.nttdata.emeal.msvc.product.exceptions.ProductNotFoundException;
import com.nttdata.emeal.msvc.product.mapper.ProductMapper;
import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.repository.ProductRepository;
import com.nttdata.emeal.msvc.product.service.*;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

  @Autowired
  private CommissionService commissionService;

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
  public Single<SavingsAccount> createSavingsAccount(ProductDTO productDTO) {
    return productRepository
      .getProductsByIdClient(productDTO.getIdClient())
      .toList()
      .observeOn(Schedulers.io())
      .flatMap(products -> validateSavingsAccountAndSave(productDTO, products));
  }

  private Single<SavingsAccount> validateSavingsAccountAndSave(ProductDTO productDTO, List<ProductDTO> products) {
    Client client = clientService.retrieveAClient(productDTO.getIdClient()).blockingGet();
    List<ProductDTO> savingsAccountFiltered = products.stream().filter(productFounded -> !productFounded.getProductType().equals(BANK_CREDIT) && productFounded.getAccountBankType().equals(SAVINGS_ACCOUNT)).collect(Collectors.toList());
    if (savingsAccountFiltered.isEmpty() &&
      !Objects.equals(Objects.requireNonNull(client).getClientType(), ENTERPRISE) &&
      Objects.requireNonNull(client).getClientType().equals(PERSONAL)
    ) {
      return productMapper.mapSavingsAccountAndSave(productDTO);
    } else {
      return Single.error(new Throwable("The client already has an equal product"));
    }
  }

  @Override
  @Transactional
  public Single<CheckingAccount> createCheckingAccount(ProductDTO productDTO) {
    return productRepository
      .getProductsByIdClient(productDTO.getIdClient())
      .toList()
      .flatMap(products -> validateCheckingAccountAndSave(productDTO, products));
  }

  private Single<CheckingAccount> validateCheckingAccountAndSave(ProductDTO productDTO, List<ProductDTO> products) {
    Client client = clientService.retrieveAClient(productDTO.getIdClient()).blockingGet();
    List<ProductDTO> checkingAccountFiltered = products.stream().filter(productFounded -> !productFounded.getProductType().equals(BANK_CREDIT) && productFounded.getAccountBankType().equals(CHECKING_ACCOUNT)).collect(Collectors.toList());
    if (checkingAccountFiltered.isEmpty() &&
      Objects.requireNonNull(client).getClientType().equals(PERSONAL)) {
      return productMapper.mapCheckingAccountAndSave(productDTO);
    } else if (Objects.requireNonNull(client).getClientType().equals(ENTERPRISE)) {
      return productMapper.mapCheckingAccountAndSave(productDTO);
    } else {
      return Single.error(new Throwable("The client already has an equal product"));
    }
  }

  @Override
  @Transactional
  public Single<FixedTermAccount> createFixedTermAccount(ProductDTO productDTO) {
    return productRepository
      .getProductsByIdClient(productDTO.getIdClient())
      .toList()
      .flatMap(products -> {
        Client client = clientService.retrieveAClient(productDTO.getIdClient()).blockingGet();
        List<ProductDTO> fixedTermAccountFiltered = products.stream().filter(productFounded -> !productFounded.getProductType().equals(BANK_CREDIT) && productFounded.getAccountBankType().equals(FIX_TERM_ACCOUNT)).collect(Collectors.toList());
        if (fixedTermAccountFiltered.isEmpty() &&
          !Objects.equals(Objects.requireNonNull(client).getClientType(), ENTERPRISE) &&
          Objects.requireNonNull(client).getClientType().equals(PERSONAL)
        ) {
          return productMapper.mapFixedTermAccountAndSave(productDTO);
        } else {
          return Single.error(new Throwable("The client already has an equal product"));
        }
      });
  }

  @Override
  @Transactional
  public Single<LoanBank> createLoanBank(ProductDTO productDTO) {
    validateDebtsOfAClient(productDTO.getIdClient());
    return productMapper.mapLoanBankAndSave(productDTO);
  }

  @Override
  @Transactional
  public Single<CreditCard> createCreditCard(ProductDTO productDTO) {
    validateDebtsOfAClient(productDTO.getIdClient());
    return productMapper.mapCreditCardAndSave(productDTO);
  }

  private void validateDebtsOfAClient(String idClient) {
    Flowable<Debt> debtsOfClient = debtService.getAllDebtsByClientId(idClient);
    debtsOfClient.subscribe(debt -> {
      if ( debt.getExpirationDate().compareTo(LocalDate.now().toString()) > 0) {
        throw new ExpiredDebtExpection("The client has an overdue debt.");
      }
    }).dispose();
  }

  @Override
  @Transactional
  public Single<SavingsAccount> updateSavingsAccount(String productId, ProductDTO productDTO) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Savings account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapSavingsAccountAndUpdate(productFounded, productDTO));
  }

  @Override
  @Transactional
  public Single<CheckingAccount> updateCheckingAccount(String productId, ProductDTO productDTO) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Checking account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCheckingAccountAndUpdate(productFounded, productDTO));
  }

  @Override
  @Transactional
  public Single<FixedTermAccount> updateFixedTermAccount(String productId, ProductDTO productDTO) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Fixed term account not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapFixedTermAccountAndUpdate(productFounded, productDTO));
  }

  @Override
  @Transactional
  public Single<LoanBank> updateLoanBank(String productId, ProductDTO productDTO) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Loan bank not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapLoanBankAndUpdate(productFounded, productDTO));
  }

  @Override
  @Transactional
  public Single<CreditCard> updateCreditCard(String productId, ProductDTO productDTO) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Credit card not found with id: " + productId)))
      .flatMap(productFounded -> productMapper.mapCreditCardAndUpdate(productFounded, productDTO));
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
  public Flowable<ProductDTO> getAllProductsByClientId(String clientId) {
    return productRepository
      .getProductsByIdClient(clientId)
      .switchIfEmpty(Flowable.error(new ProductNotFoundException("The client currently has no products")));
  }

  @Override
  public Completable deposit(Deposit deposit) {
    return Completable.fromAction(() -> {
      BankAccount sourceProduct = (BankAccount) productRepository.findById(deposit.getSourceProductId()).blockingGet();
      assert sourceProduct != null;
      if ( sourceProduct.getMaxTransactionLimit() <= 0) {
        BigDecimal feeAmount = sourceProduct.transactionFee();
        commissionService.saveCommission(Commission.builder().productId(sourceProduct.getId()).amount(feeAmount).build()).subscribe();
      }
      sourceProduct.deposit(deposit.getAmount());
      sourceProduct.doATransaction();
      productRepository.save(sourceProduct).subscribe();
      Transaction transaction = Transaction.builder().transactionType(DEPOSIT).dateTransaction(new Date().toString()).productId(deposit.getSourceProductId()).build();
//      transactionService.saveATransaction(transaction);
    });
  }

  @Override
  public Completable withdrawal(Withdrawal withdrawal) {
    return productRepository
      .findProductById(withdrawal.getSourceProductId())
      .flatMapCompletable(sourceProduct -> {
        BankAccount bankAccount = (BankAccount) sourceProduct;
        if ( bankAccount.getMaxTransactionLimit() <= 0) {
          BigDecimal feeAmount = bankAccount.transactionFee();
          commissionService.saveCommission(Commission.builder().productId(bankAccount.getId()).amount(feeAmount).build()).subscribe();
        }
        bankAccount.withdrawal(withdrawal.getAmount());
        bankAccount.doATransaction();
        productRepository.save(bankAccount).subscribe();
        Transaction transaction = Transaction.builder().transactionType(WITHDRAWAL).dateTransaction(new Date().toString()).productId(withdrawal.getSourceProductId()).build();
//        transactionService.saveATransaction(transaction).subscribe();
        return Completable.complete();
      });
  }

  @Override
  public Completable payCreditProduct(PayCreditProductDTO payCreditProductDTO) {
    return Completable.fromAction(() -> {
      Product product = productRepository.findById(payCreditProductDTO.getProductId()).blockingGet();
      List<Debt> debtsByProductId = debtService.getAllDebtsByProductId(payCreditProductDTO.getProductId()).toList().blockingGet();
      List<Debt> debtFiltered = debtsByProductId.stream().filter(debt -> debt.getIdProduct().equals(product.getId())).collect(Collectors.toList());
      debtFiltered.forEach(debt -> {
        if ( debt.getAmount().compareTo(BigDecimal.ZERO) > 0) {
          debt.setAmount(debt.getAmount().subtract(payCreditProductDTO.getAmount()));
          debtService.updateDebt(debt.getId(), debt).subscribe();
        }
      });
    });
  }

  @Override
  public Completable chargeConsumptionToCreditCard(ChargeConsumptionToCreditCardDTO chargeConsumptionToCreditCardDTO) {
    return productRepository
      .findProductById(chargeConsumptionToCreditCardDTO.getProductId())
      .flatMapCompletable(product -> {
        CreditCard creditCard = (CreditCard) product;
        BigDecimal substractResult = creditCard.getCurrentLine().subtract(chargeConsumptionToCreditCardDTO.getCharge());
        if (substractResult.compareTo(BigDecimal.ZERO) < 0) {
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

  @Override
  public Single<SavingsAccount> createSavingsAccountVipClient(SavingsAccountVipClientDTO savingsAccountVipClientDTO) {
    return productRepository
      .getProductsByIdClient(savingsAccountVipClientDTO.getIdClient())
      .toList()
      .flatMap(products -> validateSavingsAccountVipClientAndSave(savingsAccountVipClientDTO, products));
  }

  private Single<SavingsAccount> validateSavingsAccountVipClientAndSave(SavingsAccountVipClientDTO savingsAccountVipClientDTO, List<ProductDTO> products) {
    products.forEach(product -> {
      if ((product.getProductType().equals(BANK_CREDIT) || product.getProductType().equals(CREDIT_CARD)) &&
        savingsAccountVipClientDTO.getAmount().compareTo(BigDecimal.valueOf(500)) > 0) {
        productRepository.save(productMapper.mapProductDTOToProduct(product)).subscribe();
      }
    });
    return Single.never();
  }

  @Override
  public Single<CheckingAccount> createCheckingAccountPymeClient(CheckingAccountPymeClientDTO checkingAccountPymeClientDTO) {
    return productRepository
      .getProductsByIdClient(checkingAccountPymeClientDTO.getIdClient())
      .toList()
      .flatMap(this::validateCheckingAccountPymeClientAndSave);
  }

  private Single<CheckingAccount> validateCheckingAccountPymeClientAndSave(List<ProductDTO> products) {
    products.forEach(product -> {
      if (product.getProductType().equals(BANK_CREDIT) || product.getProductType().equals(CREDIT_CARD)) {
        productRepository.save(productMapper.mapProductDTOToProduct(product)).subscribe();
      }
    });
    return Single.never();
  }

  @Override
  public Completable bankTransferToOtherAccount(BankTransferToOtherAccountDTO bankTransferToOtherAccountDTO) {
    return Completable.fromAction(() -> {
      BankAccount sourceAccount = (BankAccount) productRepository.findById(bankTransferToOtherAccountDTO.getSourceProductId()).blockingGet();
      BankAccount targetAccount = (BankAccount) productRepository.findById(bankTransferToOtherAccountDTO.getTargetProductId()).blockingGet();
      sourceAccount.withdrawal(bankTransferToOtherAccountDTO.getAmount());
      targetAccount.deposit(bankTransferToOtherAccountDTO.getAmount());
      productRepository.save(sourceAccount).subscribe();
      productRepository.save(targetAccount).subscribe();
    });
  }

  @Override
  public Flowable<Commission> getAllCommissionsCurrentMonthByProductId(String productId) {
    return commissionService.getAllCommissionsCurrentMonthByProductId(productId);
  }

  @Override
  public Single<DebitCard> associateAccountToDebitCard(AssociateAccountToDebitCardDTO associateAccountToDebitCardDTO) {
    return productMapper.mapDebitCardAndSave(associateAccountToDebitCardDTO);
  }

  @Override
  public Completable payWithDebitCard(PayWithDebitCardDTO payWithDebitCardDTO) {
    return Completable.fromAction(() -> {
      BankAccount sourceProduct = (BankAccount) productRepository.findById(payWithDebitCardDTO.getSourceProductId()).blockingGet();
      BankCredit targetProduct =  (BankCredit) productRepository.findById(payWithDebitCardDTO.getTargetProductId()).blockingGet();

      sourceProduct.setBalance(sourceProduct.getBalance().subtract(payWithDebitCardDTO.getAmountToPay()));
      targetProduct.setTotalDebt(targetProduct.getTotalDebt().subtract(payWithDebitCardDTO.getAmountToPay()));
      productRepository.save(sourceProduct).subscribe();
      productRepository.save(targetProduct).subscribe();

    });
  }

  @Override
  public Single<DebitCard> establishMainAccountToDebitCard(EstablishMainAccountToDebitCardDTO establishMainAccountToDebitCardDTO) {
    return productRepository
      .findById(establishMainAccountToDebitCardDTO.getDebitCardId())
      .switchIfEmpty(Single.error(new ProductNotFoundException("Product not found with id: " + establishMainAccountToDebitCardDTO.getDebitCardId())))
      .flatMap(product -> {
        DebitCard debitCard = (DebitCard) product;
        debitCard.setMainAccountProductId(establishMainAccountToDebitCardDTO.getMainAccountId());
        return productRepository.save(debitCard);
      });
  }

  @Override
  public Flowable<Transaction> getLastTenTransactionsOfDebitCardOrCreditCard(String id) {
    return null;
  }

  @Override
  public Single<String> getBalanceOfMainAccount(String productId) {
    return productRepository
      .findById(productId)
      .switchIfEmpty(Single.error(new ProductNotFoundException("Product not found with id: " + productId)))
      .flatMap(product -> {
        BankAccount bankAccount = (BankAccount) product;
        return Single.just(bankAccount.getBalance());
      }).map(object -> "Balance of main account is " + object);
  }

}
