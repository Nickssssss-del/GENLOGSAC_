package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.Quote; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface QuoteRepository extends JpaRepository<Quote,UUID> { Optional<Quote> findByNumber(String number); }