package com.tuempresa.repuestos_backend.web;

import com.tuempresa.repuestos_backend.repository.QuoteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/quotes")
public class MailController {
  private final QuoteRepository quotes; private final JavaMailSender mail; @Value("${spring.mail.username:}") private String sender;
  public MailController(QuoteRepository q, JavaMailSender m){quotes=q;mail=m;}
  @PostMapping("/{id}/send") ResponseEntity<?> send(@PathVariable UUID id,@Valid @RequestBody SendRequest request) {
    var quote=quotes.findById(id).orElse(null); if(quote==null)return ResponseEntity.notFound().build();
    if(sender.isBlank()) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error","Configura MAIL_HOST, MAIL_USERNAME y MAIL_PASSWORD para enviar correos"));
    try { var message=mail.createMimeMessage(); var helper=new MimeMessageHelper(message,true); helper.setFrom(sender); helper.setTo(request.recipient()); helper.setSubject("Cotización "+quote.getNumber()); helper.setText("Adjuntamos la cotización "+quote.getNumber()+"."); mail.send(message); return ResponseEntity.ok(Map.of("recipient",request.recipient(),"status","ENVIADA")); }
    catch(Exception error){return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Map.of("recipient",request.recipient(),"status","ERROR","error","No se pudo enviar el correo"));}
  }
  public record SendRequest(String recipient){}
}