import type { Client, Invoice, Product, PurchaseOrder, Quote, User } from '../types';

export const mockUser: User = { id: 'u1', name: 'Luis Mendoza', email: 'admin@empresa.local', role: 'ADMINISTRADOR', active: true };

export const products: Product[] = [
  { id: 'p1', name: 'Rodamiento SKF X', sku: 'SKF-6312-2Z', description: 'Rodamiento industrial sellado', brand: 'SKF', category: 'Rodamientos', unit: 'und.', status: 'ACTIVO' },
  { id: 'p2', name: 'Sello hidráulico', sku: 'SH-90-PU', description: 'Sello de poliuretano', brand: 'Trelleborg', category: 'Sellos y retenes', unit: 'und.', status: 'ACTIVO' },
  { id: 'p3', name: 'Válvula de control', sku: 'VC-4-150', description: 'Válvula para control de flujo', brand: 'Flowserve', category: 'Válvulas', unit: 'und.', status: 'ACTIVO' },
  { id: 'p4', name: 'Acople flexible KTR', sku: 'KTR-ROTEX-42', description: 'Acople flexible para transmisión', brand: 'KTR', category: 'Transmisión', unit: 'und.', status: 'ACTIVO' },
  { id: 'p5', name: 'Kit de empaquetadura', sku: 'KE-160-PTFE', description: 'Kit PTFE para mantenimiento', brand: 'Chesterton', category: 'Sellos y retenes', unit: 'kit', status: 'ACTIVO' },
];

export const clients: Client[] = [
  { id: 'c1', name: 'Minera ABC', ruc: '20100123456', contact: 'Rosa Cárdenas', email: 'compras@mineraabc.pe', city: 'Arequipa', orders: 14, total: 42860, health: 'Activo' },
  { id: 'c2', name: 'Minera XYZ', ruc: '20456789102', contact: 'Diego Salazar', email: 'abastecimiento@xyz.com.pe', city: 'Lima', orders: 9, total: 28140, health: 'Activo' },
  { id: 'c3', name: 'Minera DEF', ruc: '20567891234', contact: 'María Ponce', email: 'logistica@defmining.pe', city: 'Moquegua', orders: 6, total: 16780, health: 'Activo' },
];

export const suppliers = [
  { id: 's1', name: 'SKF Perú', ruc: '20501478521', contact: 'Carlos Núñez', category: 'Rodamientos', lead: '12–15 días', rating: 'Preferente' },
  { id: 's2', name: 'Suministros Andinos', ruc: '20608932177', contact: 'Lucía Vargas', category: 'Válvulas y sellos', lead: '8–10 días', rating: 'Activo' },
  { id: 's3', name: 'TecnoMaq Import', ruc: '20411982301', contact: 'Jorge Arias', category: 'Transmisión', lead: '20–25 días', rating: 'Activo' },
];

export const initialQuotes: Quote[] = [
  { id: 'COT-0013', client: 'Minera XYZ', date: '18 jun 2024', expires: '02 jul 2024', amount: 12840, currency: 'USD', exchangeRate: 3.78, status: 'ENVIADA', owner: 'Luis M.' },
  { id: 'COT-0012', client: 'Minera ABC', date: '14 jun 2024', expires: '28 jun 2024', amount: 7640, currency: 'USD', exchangeRate: 3.78, status: 'APROBADA', owner: 'Luis M.' },
  { id: 'COT-0011', client: 'Minera DEF', date: '11 jun 2024', expires: '25 jun 2024', amount: 3920, currency: 'PEN', exchangeRate: 3.78, status: 'BORRADOR', owner: 'Ana R.' },
  { id: 'COT-0010', client: 'Minera ABC', date: '06 jun 2024', expires: '20 jun 2024', amount: 18600, currency: 'USD', exchangeRate: 3.78, status: 'APROBADA', owner: 'Luis M.' },
];

export const initialOrders: PurchaseOrder[] = [
  { id: 'OC-45873', client: 'Minera XYZ', quote: 'COT-0013', date: '19 jun 2024', amount: 12840, status: 'En compra', delivery: '12 jul 2024' },
  { id: 'OC-45872', client: 'Minera ABC', quote: 'COT-0012', date: '15 jun 2024', amount: 7640, status: 'Entregada', delivery: '28 jun 2024' },
  { id: 'OC-45868', client: 'Minera DEF', quote: 'COT-0009', date: '07 jun 2024', amount: 4210, status: 'Pagada', delivery: '21 jun 2024' },
];

export const initialInvoices: Invoice[] = [
  { id: 'F001-260', client: 'Minera XYZ', order: 'OC-45873', issue: '20 jun 2024', due: '20 jul 2024', amount: 12840, paid: 0, status: 'Pendiente' },
  { id: 'F001-259', client: 'Minera ABC', order: 'OC-45872', issue: '17 jun 2024', due: '17 jul 2024', amount: 7640, paid: 3000, status: 'Parcial' },
  { id: 'F001-258', client: 'Minera DEF', order: 'OC-45868', issue: '08 jun 2024', due: '08 jul 2024', amount: 4210, paid: 4210, status: 'Pagada' },
];

export const initialPurchases = [
  { id: 'CP-0021', supplier: 'SKF Perú', order: 'OC-45873', date: '20 jun 2024', currency: 'USD' as const, amount: 6800, rate: 1, status: 'En compra' as const },
  { id: 'CP-0020', supplier: 'Suministros Andinos', order: 'OC-45872', date: '16 jun 2024', currency: 'PEN' as const, amount: 17320, rate: 3.78, status: 'Entregada' as const },
  { id: 'CP-0019', supplier: 'SKF Perú', order: 'OC-45868', date: '08 jun 2024', currency: 'USD' as const, amount: 2500, rate: 1, status: 'Pagada' as const },
];