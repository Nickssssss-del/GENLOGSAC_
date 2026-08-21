package com.tuempresa.repuestos_backend.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "app_users")
public class AppUser {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
  @Column(nullable=false, unique=true) private String email;
  @Column(nullable=false) private String passwordHash;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private UserRole role;
  @Column(nullable=false) private boolean active;
  protected AppUser() {}
  public AppUser(String email, String passwordHash, UserRole role, boolean active) { this.email=email; this.passwordHash=passwordHash; this.role=role; this.active=active; }
  public UUID getId(){return id;} public String getEmail(){return email;} public String getPasswordHash(){return passwordHash;} public UserRole getRole(){return role;} public boolean isActive(){return active;}
}