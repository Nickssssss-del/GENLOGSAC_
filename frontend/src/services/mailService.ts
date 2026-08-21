import type { Quote } from '../types';
export const mailService = {
  sendQuote(quote: Quote, recipient: string) {
    return Boolean(quote.id && recipient.includes('@'));
  },
};