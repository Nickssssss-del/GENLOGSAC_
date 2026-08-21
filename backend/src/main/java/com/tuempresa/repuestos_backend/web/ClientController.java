package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.*; import com.tuempresa.repuestos_backend.repository.ClientRepository;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/clients")
public class ClientController {
 private final ClientRepository repo; public ClientController(ClientRepository r){repo=r;}
 @GetMapping List<Client> all(){return repo.findAll();}
 @GetMapping("/{id}") ResponseEntity<Client> one(@PathVariable UUID id){return repo.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping ResponseEntity<?> create(@Valid @RequestBody Client c){if(repo.existsByRuc(c.getRuc()))return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","El RUC ya está registrado"));return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(c));}
 @PatchMapping("/{id}/status") ResponseEntity<?> status(@PathVariable UUID id,@RequestBody Map<String,String> body){return repo.findById(id).map(c->{c.setStatus(RecordStatus.valueOf(body.get("status")));return ResponseEntity.ok(repo.save(c));}).orElseGet(()->ResponseEntity.notFound().build());}
}