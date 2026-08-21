package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.Invoice; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface InvoiceRepository extends JpaRepository<Invoice,UUID> {}