package com.tuempresa.repuestos_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;

@Entity @Table(name="quotes")
public class Quote {
  @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
  @Column(nullable=false,unique=true) private String number;
  @ManyToOne(optional=false) private Client client;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private QuoteStatus status=QuoteStatus.BORRADOR;
  @Column(nullable=false,length=3) private String currency;
  @Column(nullable=false,precision=14,scale=4) private BigDecimal exchangeRate;
  @Column(nullable=false,precision=14,scale=2) private BigDecimal subtotal;
  @Column(nullable=false,precision=14,scale=2) private BigDecimal tax;
  @Column(nullable=false,precision=14,scale=2) private BigDecimal total;
  private String paymentTerms; private String conditions; @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
  protected Quote() {}
  public Quote(String number,Client client,String currency,BigDecimal exchangeRate,BigDecimal subtotal,BigDecimal tax,BigDecimal total,String paymentTerms,String conditions){this.number=number;this.client=client;this.currency=currency;this.exchangeRate=exchangeRate;this.subtotal=subtotal;this.tax=tax;this.total=total;this.paymentTerms=paymentTerms;this.conditions=conditions;}
  public UUID getId(){return id;} public String getNumber(){return number;} public Client getClient(){return client;} public QuoteStatus getStatus(){return status;} public void setStatus(QuoteStatus s){status=s;} public String getCurrency(){return currency;} public BigDecimal getExchangeRate(){return exchangeRate;} public BigDecimal getSubtotal(){return subtotal;} public BigDecimal getTax(){return tax;} public BigDecimal getTotal(){return total;} public String getPaymentTerms(){return paymentTerms;} public String getConditions(){return conditions;} public LocalDateTime getCreatedAt(){return createdAt;}
}