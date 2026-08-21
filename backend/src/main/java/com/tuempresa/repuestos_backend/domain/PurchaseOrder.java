package com.tuempresa.repuestos_backend.domain;
import jakarta.persistence.*; import java.time.LocalDateTime; import java.util.UUID;
@Entity @Table(name="purchase_orders")
public class PurchaseOrder {
 @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id; @Column(nullable=false,unique=true) private String number; @ManyToOne(optional=false) private Client client; @OneToOne(optional=false) private Quote quote; @Enumerated(EnumType.STRING) @Column(nullable=false) private OrderStatus status=OrderStatus.PENDIENTE; private LocalDateTime createdAt=LocalDateTime.now();
 protected PurchaseOrder() {} public PurchaseOrder(String number,Client c,Quote q){this.number=number;client=c;quote=q;} public UUID getId(){return id;} public String getNumber(){return number;} public Client getClient(){return client;} public Quote getQuote(){return quote;} public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus s){status=s;} public LocalDateTime getCreatedAt(){return createdAt;}
}