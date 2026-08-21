package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.*; import com.tuempresa.repuestos_backend.repository.*;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal; import java.util.*;
@RestController @RequestMapping("/api/invoices")
public class InvoiceController {
 private final InvoiceRepository invoices; private final PurchaseOrderRepository orders; public InvoiceController(InvoiceRepository i,PurchaseOrderRepository o){invoices=i;orders=o;}
 @GetMapping List<Invoice> all(){return invoices.findAll();}
 @PostMapping("/from-order/{orderId}") ResponseEntity<?> fromOrder(@PathVariable UUID orderId){return orders.findById(orderId).map(o->ResponseEntity.status(HttpStatus.CREATED).body(invoices.save(new Invoice("F-"+System.currentTimeMillis(),o.getClient(),o,o.getQuote().getTotal())))).orElseGet(()->ResponseEntity.notFound().build());}
 @PatchMapping("/{id}/payment") ResponseEntity<?> payment(@PathVariable UUID id,@RequestBody Map<String,BigDecimal> b){return invoices.findById(id).map(i->{i.setPaid(b.getOrDefault("paid",BigDecimal.ZERO));return ResponseEntity.ok(invoices.save(i));}).orElseGet(()->ResponseEntity.notFound().build());}
}