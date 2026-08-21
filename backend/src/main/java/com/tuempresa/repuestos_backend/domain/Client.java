package com.tuempresa.repuestos_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.UUID;

@Entity @Table(name="clients", uniqueConstraints=@UniqueConstraint(name="uk_client_ruc", columnNames="ruc"))
public class Client {
  @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
  @NotBlank @Column(nullable=false) private String legalName;
  @NotBlank @Size(min=11,max=11) @Column(nullable=false,length=11) private String ruc;
  private String address; private String phone; private String email; private String contact;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private RecordStatus status=RecordStatus.ACTIVO;
  protected Client() {}
  public Client(String legalName,String ruc,String address,String phone,String email,String contact){this.legalName=legalName;this.ruc=ruc;this.address=address;this.phone=phone;this.email=email;this.contact=contact;}
  public UUID getId(){return id;} public String getLegalName(){return legalName;} public String getRuc(){return ruc;} public String getAddress(){return address;} public String getPhone(){return phone;} public String getEmail(){return email;} public String getContact(){return contact;} public RecordStatus getStatus(){return status;} public void setStatus(RecordStatus s){status=s;}
}