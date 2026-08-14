# Gestión de Repuestos — Frontend

Frontend demostrativo para una empresa que comercializa repuestos industriales para minería bajo pedido.

## Incluye

- Dashboard operativo con cuentas por cobrar, cotizaciones, órdenes en curso, vencimientos y margen estimado.
- Clientes con búsqueda e historial de cotizaciones, órdenes, facturas y pagos.
- Cotizaciones en USD con líneas de productos y cálculo automático de totales.
- Órdenes de compra de clientes con estados: pendiente de compra, comprado, recibido y entregado.
- Compras bajo pedido a proveedores, incluyendo compras en PEN/USD, tipo de cambio y costo equivalente en USD.
- Facturas y pagos con estados pendiente, parcial, pagada y vencida.
- Catálogo de repuestos sin inventario permanente.
- Proveedores, reportes y configuración.

## Alcance actual

Esta versión es frontend-only: usa datos locales de demostración y estado React. Las acciones se pueden recorrer desde la interfaz, pero todavía no persisten en una base de datos ni se conectan a un servicio externo.

## Ejecución dentro del workspace

```bash
pnpm install
pnpm --filter @workspace/gestion-repuestos run dev
```

Para comprobar el frontend:

```bash
pnpm --filter @workspace/gestion-repuestos run typecheck
```
