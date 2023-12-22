package com.nttdata.emeal.msvc.product.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

  private String id;
  private String idClient;
  private String productType;
  private String accountBankType;
  private BigDecimal balance;
  private Boolean isMainAccount;
  private String numCredits;
  private String creditType;
  private Integer maxTransactionLimit = 20;
  private String maintenanceFee;
  private List<String> holders;
  private List<String> signatories;
  private String specificDay;
  private String amount;
  private String dateCreated;
  private BigDecimal totalDebt;
  private String expirationDate;
  private String holderName;
  private BigDecimal totalLine;
  private BigDecimal currentLine;
  private String cutOffDate;
  private String paymentDate;
  private BigDecimal minPayment;
  private BigDecimal totalPayment;
}
