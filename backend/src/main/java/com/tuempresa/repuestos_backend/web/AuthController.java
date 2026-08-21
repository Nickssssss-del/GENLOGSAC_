package com.tuempresa.repuestos_backend.web;
import com.tuempresa.repuestos_backend.domain.AppUser; import com.tuempresa.repuestos_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; @Value("${app.admin-email}") String adminEmail; @Value("${app.admin-password}") String adminPassword;
 public AuthController(UserRepository u,PasswordEncoder e){users=u;encoder=e;}
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest body){
   AppUser user=users.findByEmail(body.email()).orElse(null);
   if(user==null || !user.isActive() || !encoder.matches(body.password(),user.getPasswordHash())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Credenciales inválidas"));
   return ResponseEntity.ok(Map.of("email",user.getEmail(),"role",user.getRole().name(),"message","Autenticación correcta","basicAuth",true));
 }
 public record LoginRequest(String email,String password){}
}