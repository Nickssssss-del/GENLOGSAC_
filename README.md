# Gestión Comercial · Fase 1

Sistema B2B para empresas que venden repuestos industriales. Esta entrega corrige
el proyecto existente y deja el frontend operable con mock data, sin rehacerlo ni
introducir todavía la base de datos completa.

## Tecnologías y estructura

- `frontend/`: React, TypeScript, Vite, Tailwind CSS y wouter.
- `backend/`: Spring Boot, Spring Data JPA, Spring Security, PostgreSQL/H2,
  PDFBox y Spring Mail.

El frontend ahora separa `pages` (las vistas existentes), `components`, `services`
(`authService`, `quoteService`, `mailService` y `mockData`), `types` y `hooks`.
Los datos de catálogo, clientes, cotizaciones, órdenes, compras y facturas se
consumen desde services; una futura API puede sustituir esos services sin cambiar
las vistas.

## Requisitos

- Java 25 y Maven (o el wrapper `backend/mvnw`).
- Node.js 18+ y pnpm.
- PostgreSQL para producción. H2 se usa por defecto para desarrollo.

## Variables de entorno

Copia `backend/.env.example` a la configuración de tu entorno. No subas archivos
`.env` ni credenciales al repositorio.

Variables principales:

```text
SERVER_PORT=8080
DATABASE_URL=jdbc:postgresql://localhost:5432/gestion_comercial
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=change-me
JPA_DDL_AUTO=update
CORS_ORIGIN=http://localhost:5173
ADMIN_EMAIL=admin@empresa.local
ADMIN_PASSWORD=change-me-in-env
MAIL_HOST=smtp.example.com
MAIL_PORT=587
MAIL_USERNAME=
MAIL_PASSWORD=
```

El usuario administrador se crea al iniciar con `ADMIN_EMAIL` y
`ADMIN_PASSWORD`. El hash nunca se devuelve por la API.

## Instalación y desarrollo

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`. Comprueba el servicio con:

```bash
curl http://localhost:8080/api/health
```

En Windows usa `mvnw.cmd spring-boot:run` si el wrapper Unix no es ejecutable.

### Frontend

En otra terminal:

```bash
cd frontend
pnpm install
PORT=5173 BASE_PATH=/ pnpm dev
```

La aplicación queda en `http://localhost:5173`.

## Funcionalidades

- Login y logout mock con roles `ADMINISTRADOR` y `USUARIO`; `authService` queda
  listo para reemplazar la sesión por el endpoint de autenticación.
- Clientes únicamente empresariales, RUC único, estado ACTIVO/INACTIVO e
  historial conservado; no se eliminan físicamente.
- Catálogo de productos sin precios visibles, con código, descripción, marca,
  categoría, unidad, imagen y estado.
- Cotizaciones PEN/USD con tipo de cambio persistido e importe manual por línea;
  el catálogo nunca suministra precios.
- Estados de cotización: BORRADOR, ENVIADA, APROBADA, RECHAZADA, VENCIDA y
  CANCELADA.
- Catálogo sin ningún precio, costo o importe visible.
- Cotizaciones PEN/USD con importe manual por línea, cantidad, subtotal, IGV,
  total y tipo de cambio guardado en la cotización.
- Descarga de un documento de cotización en Fase 1 y service preparado para
  conectar `GET /api/quotes/{id}/pdf`.
- Envío de correo simulado desde service; las credenciales permanecen en backend.
- Relación Cliente → Cotización → Orden → Factura.
- Estados de orden: PENDIENTE, APROBADA, EN_PROCESO, ATENDIDA y CANCELADA.
- Dashboard y frontend responsive conservando la navegación existente.

## API principal

```text
POST  /api/auth/login
GET   /api/health
GET   /api/clients
POST  /api/clients
PATCH /api/clients/{id}/status
GET   /api/catalog
POST  /api/catalog
PUT   /api/catalog/{id}
PATCH /api/catalog/{id}/status
GET   /api/quotes
POST  /api/quotes
PATCH /api/quotes/{id}/status
GET   /api/quotes/{id}/pdf
POST  /api/quotes/{id}/send
GET   /api/orders
POST  /api/orders/from-quote/{quoteId}
PATCH /api/orders/{id}/status
GET   /api/invoices
POST  /api/invoices/from-order/{orderId}
PATCH /api/invoices/{id}/payment
```

Las rutas protegidas requieren autenticación HTTP Basic con las credenciales
configuradas para el administrador.

## PDF y correo

`GET /api/quotes/{id}/pdf` genera el documento con empresa, cliente, RUC, fecha,
número, productos, cantidades, importes, subtotal, IGV, total, moneda, tipo de
cambio y condiciones.

Para enviar correo configura `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME` y
`MAIL_PASSWORD`. Si SMTP no está configurado, la cotización permanece guardada y
el PDF sigue disponible.

## Producción

```bash
cd frontend
PORT=5173 BASE_PATH=/ pnpm build
```

Para producción usa PostgreSQL, `JPA_DDL_AUTO=validate`, HTTPS, contraseñas en
un gestor de secretos y un origen CORS explícito. Nunca uses la contraseña
predeterminada del archivo de ejemplo.

## Pruebas

```bash
cd frontend
pnpm typecheck
PORT=5173 BASE_PATH=/ pnpm build

cd ../backend
./mvnw clean verify
```

En el archivo recibido el wrapper no conserva permiso de ejecución: si el sistema
lo requiere, usa `bash mvnw clean verify` o `mvnw.cmd clean verify` en Windows.

### Verificación realizada en esta entrega

- `cd frontend && pnpm typecheck` ✅
- `PORT=5173 BASE_PATH=/ pnpm build` ✅
- La verificación Java queda preparada, pero requiere un JDK configurado en
  `JAVA_HOME` en el equipo que ejecute Maven.

## Fase 1 vs. Fase 2

### Funciona con mock data

Login/logout, dashboard, clientes, historial, catálogo sin precios, creación de
cotizaciones, cálculo de subtotal/IGV/total, moneda y tipo de cambio, PDF de
cotización, preparación de correo, órdenes, compras, facturas, pagos, reportes y
preferencias.

### Preparado para API y BD

El backend conserva endpoints de auth, clients, catalog/products, quotes, PDF,
mail, orders e invoices. Los contratos están descritos en la sección API y los
services del frontend son el punto de reemplazo para llamadas HTTP.

### Pendiente para Fase 2

Persistencia completa y migraciones, reemplazar mocks por llamadas autenticadas,
permisos por rol, generación PDF con datos completos en backend, envío SMTP
productivo, auditoría e integración con un proveedor de facturación electrónica.

Errores frecuentes:

- `401`: revisa correo y contraseña del administrador.
- Error de conexión: revisa URL, usuario, contraseña y disponibilidad de
  PostgreSQL.
- `503` al enviar correo: configura las variables SMTP.
- En Windows, usa `mvnw.cmd` o Git Bash si el wrapper no tiene permisos.
