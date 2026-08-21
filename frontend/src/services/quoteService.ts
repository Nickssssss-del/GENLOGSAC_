import type { Quote } from '../types';

export const quoteService = {
  calculate(lines: { qty: number; amount: number }[]) {
    const subtotal = lines.reduce((sum, line) => sum + line.qty * line.amount, 0);
    const igv = subtotal * 0.18;
    return { subtotal, igv, total: subtotal + igv };
  },
  downloadPdf(quote: Quote) {
    const safe = (value: string) => value.replace(/[()\\]/g, '\\$&');
    const lines = [
      `COTIZACION ${quote.id}`,
      `Cliente: ${quote.client}`,
      `Fecha: ${quote.date}`,
      `Moneda: ${quote.currency ?? 'USD'}  Tipo de cambio: ${quote.exchangeRate ?? 1}`,
      `Subtotal: ${quote.subtotal ?? quote.amount}`,
      `IGV (18%): ${quote.igv ?? 0}`,
      `TOTAL: ${quote.total ?? quote.amount}`,
      'Forma de pago y condiciones: segun propuesta comercial.',
    ];
    const stream = `BT /F1 12 Tf 50 790 Td ${lines.map((line, index) => `${index ? '0 -24 Td ' : ''}(${safe(line)}) Tj`).join(' ')} ET`;
    const objects = [
      '<< /Type /Catalog /Pages 2 0 R >>',
      '<< /Type /Pages /Kids [3 0 R] /Count 1 >>',
      '<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 842] /Resources << /Font << /F1 4 0 R >> >> /Contents 5 0 R >>',
      '<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>',
      `<< /Length ${stream.length} >>\nstream\n${stream}\nendstream`,
    ];
    let pdf = '%PDF-1.4\n'; const offsets: number[] = [0];
    objects.forEach((object, index) => { offsets[index + 1] = pdf.length; pdf += `${index + 1} 0 obj\n${object}\nendobj\n`; });
    const xref = pdf.length; pdf += `xref\n0 ${objects.length + 1}\n0000000000 65535 f \n${offsets.slice(1).map(offset => `${String(offset).padStart(10, '0')} 00000 n `).join('\n')}\ntrailer\n<< /Size ${objects.length + 1} /Root 1 0 R >>\nstartxref\n${xref}\n%%EOF`;
    const blob = new Blob([pdf], { type: 'application/pdf' });
    const url = URL.createObjectURL(blob);
    const anchor = document.createElement('a'); anchor.href = url; anchor.download = `${quote.id}.html`; anchor.click(); URL.revokeObjectURL(url);
  },
};