package com.tuempresa.repuestos_backend.domain;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.UUID;
@Entity @Table(name="invoices")
public class Invoice {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id; @Column(nullable=false,unique=true) private String number; @ManyToOne(optional=false) private Client client; @OneToOne(optional=false) private PurchaseOrder order; @Column(nullable=false) private BigDecimal total; private BigDecimal paid=BigDecimal.ZERO; private LocalDateTime createdAt=LocalDateTime.now();
 protected Invoice() {} public Invoice(String number,Client c,PurchaseOrder o,BigDecimal total){this.number=number;client=c;order=o;this.total=total;} public UUID getId(){return id;} public String getNumber(){return number;} public Client getClient(){return client;} public PurchaseOrder getOrder(){return order;} public BigDecimal getTotal(){return total;} public BigDecimal getPaid(){return paid;} public void setPaid(BigDecimal p){paid=p;} public LocalDateTime getCreatedAt(){return createdAt;}
}