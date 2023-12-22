package com.nttdata.emeal.msvc.product.mapper;

import com.nttdata.emeal.msvc.product.dto.AssociateAccountToDebitCardDTO;
import com.nttdata.emeal.msvc.product.dto.ProductDTO;
import com.nttdata.emeal.msvc.product.model.*;
import com.nttdata.emeal.msvc.product.repository.ProductRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nttdata.emeal.msvc.product.utils.Constants.*;

@Component
public class ProductMapper {


  @Autowired
  private ProductRepository productRepository;

  public Single<SavingsAccount> mapSavingsAccountAndSave(ProductDTO productDTO) {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setIdClient(productDTO.getIdClient());
    savingsAccount.setProductType(BANK_ACCOUNT);
    savingsAccount.setAccountBankType(SAVINGS_ACCOUNT);
    savingsAccount.setBalance(productDTO.getBalance());
    savingsAccount.setMaxTransactionLimit(productDTO.getMaxTransactionLimit() != null ? productDTO.getMaxTransactionLimit() : 20);
    savingsAccount.setIsMainAccount(productDTO.getIsMainAccount());
    return productRepository.save(savingsAccount);
  }

  public Single<SavingsAccount> mapSavingsAccountAndUpdate(Product productFounded, SavingsAccount savingsAccountDTO) {
    SavingsAccount savingsAccount = new SavingsAccount();
    savingsAccount.setId(productFounded.getId());
    savingsAccount.setIdClient(productFounded.getIdClient());
    savingsAccount.setProductType(BANK_ACCOUNT);
    savingsAccount.setAccountBankType(SAVINGS_ACCOUNT);
    savingsAccount.setBalance(savingsAccountDTO.getBalance());
    savingsAccount.setMaxTransactionLimit(savingsAccountDTO.getMaxTransactionLimit() != null ? savingsAccountDTO.getMaxTransactionLimit() : 20);
    savingsAccount.setIsMainAccount(savingsAccountDTO.getIsMainAccount());
    return productRepository.save(savingsAccount);
  }

  public Single<CheckingAccount> mapCheckingAccountAndSave(ProductDTO productDTO) {
    CheckingAccount checkingAccount = new CheckingAccount();
    checkingAccount.setIdClient(productDTO.getIdClient());
    checkingAccount.setProductType(BANK_ACCOUNT);
    checkingAccount.setAccountBankType(CHECKING_ACCOUNT);
    checkingAccount.setBalance(productDTO.getBalance());
    checkingAccount.setMaintenanceFee("3.50");
    checkingAccount.setHolders(productDTO.getHolders());
    checkingAccount.setSignatories(productDTO.getSignatories());
    checkingAccount.setIsMainAccount(productDTO.getIsMainAccount());
    return productRepository.save(checkingAccount);
  }

  public Single<CheckingAccount> mapCheckingAccountAndUpdate(Product productFounded, CheckingAccount checkingAccountDTO) {
    CheckingAccount checkingAccount = new CheckingAccount();
    checkingAccount.setId(productFounded.getId());
    checkingAccount.setIdClient(productFounded.getIdClient());
    checkingAccount.setProductType(BANK_ACCOUNT);
    checkingAccount.setAccountBankType(CHECKING_ACCOUNT);
    checkingAccount.setBalance(checkingAccountDTO.getBalance());
    checkingAccount.setMaintenanceFee("3.50");
    checkingAccount.setHolders(checkingAccountDTO.getHolders());
    checkingAccount.setSignatories(checkingAccountDTO.getSignatories());
    checkingAccount.setIsMainAccount(checkingAccountDTO.getIsMainAccount());
    return productRepository.save(checkingAccount);
  }

  public Single<FixedTermAccount> mapFixedTermAccountAndSave(ProductDTO productDTO) {
    FixedTermAccount fixedTermAccount = new FixedTermAccount();
    fixedTermAccount.setIdClient(productDTO.getIdClient());
    fixedTermAccount.setProductType(BANK_ACCOUNT);
    fixedTermAccount.setAccountBankType(FIX_TERM_ACCOUNT);
    fixedTermAccount.setBalance(productDTO.getBalance());
    fixedTermAccount.setSpecificDay(productDTO.getSpecificDay());
    fixedTermAccount.setIsMainAccount(productDTO.getIsMainAccount());
    return productRepository.save(fixedTermAccount);
  }


  public Single<FixedTermAccount> mapFixedTermAccountAndUpdate(Product productFounded, FixedTermAccount fixedTermAccountDTO) {
    FixedTermAccount fixedTermAccount = new FixedTermAccount();
    fixedTermAccount.setId(productFounded.getId());
    fixedTermAccount.setIdClient(productFounded.getIdClient());
    fixedTermAccount.setProductType(BANK_ACCOUNT);
    fixedTermAccount.setAccountBankType(FIX_TERM_ACCOUNT);
    fixedTermAccount.setBalance(fixedTermAccountDTO.getBalance());
    fixedTermAccount.setSpecificDay(fixedTermAccountDTO.getSpecificDay());
    fixedTermAccount.setIsMainAccount(fixedTermAccountDTO.getIsMainAccount());
    return productRepository.save(fixedTermAccount);
  }



  public Single<LoanBank> mapLoanBankAndSave( LoanBank loanBankDTO) {
    LoanBank loanBank = new LoanBank();
    loanBank.setIdClient(loanBankDTO.getIdClient());
    loanBank.setProductType(BANK_CREDIT);
    loanBank.setCreditType(LOAN_BANK);
    loanBank.setAmount(loanBankDTO.getAmount());
    loanBank.setDateCreated(loanBankDTO.getDateCreated());
    return productRepository.save(loanBank);
  }

  public Single<LoanBank> mapLoanBankAndUpdate(Product productFounded, LoanBank loanBankDTO) {
    LoanBank loanBank = new LoanBank();
    loanBank.setId(productFounded.getId());
    loanBank.setIdClient(productFounded.getIdClient());
    loanBank.setProductType(BANK_CREDIT);
    loanBank.setCreditType(LOAN_BANK);
    loanBank.setAmount(loanBankDTO.getAmount());
    loanBank.setDateCreated(loanBankDTO.getDateCreated());
    return productRepository.save(loanBank);
  }

  public Single<CreditCard> mapCreditCardAndSave(CreditCard creditCardDTO) {
    CreditCard creditCard = new CreditCard();
    creditCard.setIdClient(creditCardDTO.getIdClient());
    creditCard.setProductType(BANK_CREDIT);
    creditCard.setCreditType(CREDIT_CARD);
    creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
    creditCard.setHolderName(creditCardDTO.getHolderName());
    creditCard.setTotalLine(creditCardDTO.getTotalLine());
    creditCard.setCurrentLine(creditCardDTO.getCurrentLine());
    creditCard.setCurrentBalance(creditCardDTO.getCurrentBalance());
    creditCard.setCutOffDate(creditCardDTO.getCutOffDate());
    creditCard.setPaymentDate(creditCardDTO.getPaymentDate());
    creditCard.setMinPayment(creditCardDTO.getMinPayment());
    creditCard.setTotalPayment(creditCardDTO.getTotalPayment());
    return productRepository.save(creditCard);
  }

  public Single<CreditCard> mapCreditCardAndUpdate(Product productFounded, CreditCard creditCardDTO) {
    CreditCard creditCard = new CreditCard();
    creditCard.setId(productFounded.getId());
    creditCard.setIdClient(productFounded.getIdClient());
    creditCard.setProductType(BANK_CREDIT);
    creditCard.setCreditType(CREDIT_CARD);
    creditCard.setExpirationDate(creditCardDTO.getExpirationDate());
    creditCard.setHolderName(creditCardDTO.getHolderName());
    creditCard.setTotalLine(creditCardDTO.getTotalLine());
    creditCard.setCurrentLine(creditCardDTO.getCurrentLine());
    creditCard.setCurrentBalance(creditCardDTO.getCurrentBalance());
    creditCard.setCutOffDate(creditCardDTO.getCutOffDate());
    creditCard.setPaymentDate(creditCardDTO.getPaymentDate());
    creditCard.setMinPayment(creditCardDTO.getMinPayment());
    creditCard.setTotalPayment(creditCardDTO.getTotalPayment());
    return productRepository.save(creditCard);
  }

  public Single<DebitCard> mapDebitCardAndSave(AssociateAccountToDebitCardDTO dto) {
    DebitCard debitCard = new DebitCard();
    debitCard.addAccountProductIdToList(dto.getProductId());
    return productRepository.save(debitCard);
  }

  public Product mapProductDTOToProduct(ProductDTO productDTO) {
    Product product = new Product();
    BeanUtils.copyProperties(productDTO, product);
    return product;
  }



}
