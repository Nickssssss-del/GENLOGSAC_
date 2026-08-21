import type { User } from '../types';
import { mockUser } from './mockData';

const SESSION_KEY = 'nucleo.session';
export const authService = {
  login(email: string, password: string): User | null {
    if (!email.trim() || !password.trim()) return null;
    sessionStorage.setItem(SESSION_KEY, JSON.stringify(mockUser));
    return mockUser;
  },
  currentUser(): User | null {
    try { return JSON.parse(sessionStorage.getItem(SESSION_KEY) ?? 'null') as User | null; } catch { return null; }
  },
  logout() { sessionStorage.removeItem(SESSION_KEY); },
};