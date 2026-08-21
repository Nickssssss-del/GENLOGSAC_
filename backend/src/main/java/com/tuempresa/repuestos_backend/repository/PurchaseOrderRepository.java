package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.PurchaseOrder; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,UUID> {}