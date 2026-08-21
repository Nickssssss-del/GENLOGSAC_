package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.AppUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<AppUser,UUID> { Optional<AppUser> findByEmail(String email); }