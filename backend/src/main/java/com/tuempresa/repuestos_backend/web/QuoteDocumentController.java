package com.tuempresa.repuestos_backend.web;

import com.tuempresa.repuestos_backend.domain.Quote;
import com.tuempresa.repuestos_backend.repository.QuoteLineRepository;
import com.tuempresa.repuestos_backend.repository.QuoteRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/api/quotes")
public class QuoteDocumentController {
  private final QuoteRepository quotes; private final QuoteLineRepository lines;
  public QuoteDocumentController(QuoteRepository q, QuoteLineRepository l){quotes=q;lines=l;}

  @GetMapping(value="/{id}/pdf", produces=MediaType.APPLICATION_PDF_VALUE)
  ResponseEntity<byte[]> pdf(@PathVariable UUID id) throws Exception {
    Quote quote=quotes.findById(id).orElse(null); if(quote==null)return ResponseEntity.notFound().build();
    try (PDDocument document=new PDDocument(); ByteArrayOutputStream output=new ByteArrayOutputStream()) {
      PDPage page=new PDPage(); document.addPage(page);
      try(PDPageContentStream stream=new PDPageContentStream(document,page)){
        stream.beginText(); stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD),16); stream.newLineAtOffset(48,740);
        stream.showText("Nucleo Repuestos Industriales"); stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA),11);
        stream.newLineAtOffset(0,-28); stream.showText("Cotizacion "+quote.getNumber()); stream.newLineAtOffset(0,-18); stream.showText("Cliente: "+quote.getClient().getLegalName()+" | RUC: "+quote.getClient().getRuc());
        stream.newLineAtOffset(0,-18); stream.showText("Moneda: "+quote.getCurrency()+" | Tipo de cambio: "+quote.getExchangeRate()); stream.newLineAtOffset(0,-28);
        for(var line: lines.findByQuoteId(id)){stream.showText(line.getProduct().getCode()+" - "+line.getProduct().getName()+" | Cant.: "+line.getQuantity()+" | Importe: "+line.getAmount());stream.newLineAtOffset(0,-16);}
        stream.newLineAtOffset(0,-14); stream.showText("Subtotal: "+quote.getSubtotal()); stream.newLineAtOffset(0,-16); stream.showText("IGV: "+quote.getTax()); stream.newLineAtOffset(0,-16); stream.showText("Total: "+quote.getTotal()); stream.endText();
      }
      document.save(output); return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+quote.getNumber()+".pdf").body(output.toByteArray());
    }
  }
}