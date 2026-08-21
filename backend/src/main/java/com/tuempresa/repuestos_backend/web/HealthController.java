package com.tuempresa.repuestos_backend.web;
import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api")
public class HealthController { @GetMapping("/health") Map<String,String> health(){return Map.of("status","ok","service","gestion-comercial");} }