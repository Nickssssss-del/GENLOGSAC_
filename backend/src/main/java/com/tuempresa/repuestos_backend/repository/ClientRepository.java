package com.tuempresa.repuestos_backend.repository;
import com.tuempresa.repuestos_backend.domain.Client; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ClientRepository extends JpaRepository<Client,UUID> { boolean existsByRuc(String ruc); List<Client> findByStatus(com.tuempresa.repuestos_backend.domain.RecordStatus status); }