package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.Product; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ProductRepository extends JpaRepository<Product,UUID> { Optional<Product> findByCode(String code); }