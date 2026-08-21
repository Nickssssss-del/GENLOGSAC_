export type RecordStatus = 'ACTIVO' | 'INACTIVO';
export type UserRole = 'ADMINISTRADOR' | 'USUARIO';
export type Currency = 'PEN' | 'USD';
export type QuoteStatus = 'BORRADOR' | 'ENVIADA' | 'APROBADA' | 'RECHAZADA' | 'VENCIDA' | 'CANCELADA';

export interface User {
  id: string;
  name: string;
  email: string;
  role: UserRole;
  active: boolean;
}

export interface Client {
  id: string;
  name: string;
  ruc: string;
  address?: string;
  phone?: string;
  contact: string;
  email: string;
  city?: string;
  orders: number;
  total: number;
  health: 'Activo' | 'Inactivo';
}

export interface Product {
  id: string;
  name: string;
  sku: string;
  description?: string;
  brand?: string;
  category: string;
  unit: string;
  image?: string;
  status?: RecordStatus;
}

export interface QuoteItem {
  product: string;
  qty: number;
  amount: number;
}

export interface Quote {
  id: string;
  client: string;
  date: string;
  expires: string;
  amount: number;
  subtotal?: number;
  igv?: number;
  total?: number;
  currency?: Currency;
  exchangeRate?: number;
  status: QuoteStatus | string;
  owner: string;
}

export interface PurchaseOrder {
  id: string;
  client: string;
  quote: string;
  date: string;
  amount: number;
  status: string;
  delivery: string;
}

export interface Invoice {
  id: string;
  client: string;
  order: string;
  issue: string;
  due: string;
  amount: number;
  paid: number;
  status: string;
}