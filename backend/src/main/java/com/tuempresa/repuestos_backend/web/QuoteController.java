package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.*; import com.tuempresa.repuestos_backend.repository.*;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.math.BigDecimal; import java.util.*;
@RestController @RequestMapping("/api/quotes")
public class QuoteController {
 private final QuoteRepository quotes; private final ClientRepository clients; private final ProductRepository products; private final QuoteLineRepository lines;
 public QuoteController(QuoteRepository q,ClientRepository c,ProductRepository p,QuoteLineRepository l){quotes=q;clients=c;products=p;lines=l;}
 @GetMapping List<Quote> all(){return quotes.findAll();}
 @GetMapping("/{id}") ResponseEntity<?> one(@PathVariable UUID id){return quotes.findById(id).map(q->ResponseEntity.ok(Map.of("quote",q,"lines",lines.findByQuoteId(id)))).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping ResponseEntity<?> create(@Valid @RequestBody QuoteRequest r){
  var client=clients.findById(r.clientId()).orElseThrow(); var q=quotes.save(new Quote(r.number(),client,r.currency(),r.exchangeRate(),r.subtotal(),r.tax(),r.total(),r.paymentTerms(),r.conditions()));
  if(r.lines()!=null) r.lines().forEach(x->products.findById(x.productId()).ifPresent(p->lines.save(new QuoteLine(q,p,x.quantity(),x.amount()))));
  return ResponseEntity.status(HttpStatus.CREATED).body(q);
 }
 @PatchMapping("/{id}/status") ResponseEntity<?> status(@PathVariable UUID id,@RequestBody Map<String,String> b){return quotes.findById(id).map(q->{q.setStatus(QuoteStatus.valueOf(b.get("status")));return ResponseEntity.ok(quotes.save(q));}).orElseGet(()->ResponseEntity.notFound().build());}
 public record QuoteRequest(String number,UUID clientId,String currency,BigDecimal exchangeRate,BigDecimal subtotal,BigDecimal tax,BigDecimal total,String paymentTerms,String conditions,List<LineRequest> lines){}
 public record LineRequest(UUID productId,BigDecimal quantity,BigDecimal amount){}
}