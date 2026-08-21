package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.*; import com.tuempresa.repuestos_backend.repository.*;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final PurchaseOrderRepository orders; private final QuoteRepository quotes; public OrderController(PurchaseOrderRepository o,QuoteRepository q){orders=o;quotes=q;}
 @GetMapping List<PurchaseOrder> all(){return orders.findAll();}
 @PostMapping("/from-quote/{quoteId}") ResponseEntity<?> fromQuote(@PathVariable UUID quoteId){return quotes.findById(quoteId).map(q->{if(q.getStatus()!=QuoteStatus.APROBADA)return ResponseEntity.badRequest().body(Map.of("error","Solo se puede ordenar una cotización aprobada"));return ResponseEntity.status(HttpStatus.CREATED).body(orders.save(new PurchaseOrder("OC-"+System.currentTimeMillis(),q.getClient(),q)));}).orElseGet(()->ResponseEntity.notFound().build());}
 @PatchMapping("/{id}/status") ResponseEntity<?> status(@PathVariable UUID id,@RequestBody Map<String,String> b){return orders.findById(id).map(o->{o.setStatus(OrderStatus.valueOf(b.get("status")));return ResponseEntity.ok(orders.save(o));}).orElseGet(()->ResponseEntity.notFound().build());}
}