package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.QuoteLine; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface QuoteLineRepository extends JpaRepository<QuoteLine,UUID> { List<QuoteLine> findByQuoteId(UUID quoteId); }