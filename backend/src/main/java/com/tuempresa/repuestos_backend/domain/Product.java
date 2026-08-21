package com.tuempresa.repuestos_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity @Table(name="products", uniqueConstraints=@UniqueConstraint(name="uk_product_code", columnNames="code"))
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
  @NotBlank @Column(nullable=false) private String code; @NotBlank @Column(nullable=false) private String name;
  private String description; private String brand; private String category; private String unit; private String imageUrl;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private RecordStatus status=RecordStatus.ACTIVO;
  protected Product() {}
  public Product(String code,String name,String description,String brand,String category,String unit,String imageUrl){this.code=code;this.name=name;this.description=description;this.brand=brand;this.category=category;this.unit=unit;this.imageUrl=imageUrl;}
  public UUID getId(){return id;} public String getCode(){return code;} public String getName(){return name;} public String getDescription(){return description;} public String getBrand(){return brand;} public String getCategory(){return category;} public String getUnit(){return unit;} public String getImageUrl(){return imageUrl;} public RecordStatus getStatus(){return status;} public void setStatus(RecordStatus s){status=s;}
}