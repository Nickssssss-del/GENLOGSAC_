package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.*; import com.tuempresa.repuestos_backend.repository.ProductRepository;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/catalog")
public class ProductController {
 private final ProductRepository repo; public ProductController(ProductRepository r){repo=r;}
 @GetMapping List<Product> all(){return repo.findAll();}
 @GetMapping("/{id}") ResponseEntity<Product> one(@PathVariable UUID id){return repo.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping ResponseEntity<Product> create(@Valid @RequestBody Product p){return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(p));}
 @PutMapping("/{id}") ResponseEntity<?> update(@PathVariable UUID id,@Valid @RequestBody Product incoming){return repo.findById(id).map(p->{return ResponseEntity.ok(repo.save(incoming));}).orElseGet(()->ResponseEntity.notFound().build());}
 @PatchMapping("/{id}/status") ResponseEntity<?> status(@PathVariable UUID id,@RequestBody Map<String,String> body){return repo.findById(id).map(p->{p.setStatus(RecordStatus.valueOf(body.get("status")));return ResponseEntity.ok(repo.save(p));}).orElseGet(()->ResponseEntity.notFound().build());}
}