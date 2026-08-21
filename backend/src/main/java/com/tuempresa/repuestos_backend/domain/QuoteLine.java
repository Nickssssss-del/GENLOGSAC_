package com.tuempresa.repuestos_backend.domain;
import jakarta.persistence.*; import java.math.BigDecimal; import java.util.UUID;
@Entity @Table(name="quote_lines")
public class QuoteLine {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id; @ManyToOne(optional=false) private Quote quote; @ManyToOne(optional=false) private Product product;
 @Column(nullable=false) private BigDecimal quantity; @Column(nullable=false,precision=14,scale=2) private BigDecimal amount;
 protected QuoteLine() {} public QuoteLine(Quote q,Product p,BigDecimal quantity,BigDecimal amount){quote=q;product=p;this.quantity=quantity;this.amount=amount;}
 public UUID getId(){return id;} public Product getProduct(){return product;} public BigDecimal getQuantity(){return quantity;} public BigDecimal getAmount(){return amount;}
}