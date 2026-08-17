# Gestión de Repuestos

Sistema de gestión de repuestos — backend (Spring Boot) + frontend (React/Vite).

## Estructura

```
gestion-repuestos/
├── backend/      # API REST en Spring Boot
├── frontend/     # Cliente en React + Vite
└── docs/         # Diagramas, mockups y documentación del proyecto
```

---

## Requisitos previos

Antes de clonar, instala en tu máquina:

- **Java 17+** y **Maven** (o usa el `mvnw` incluido, no requiere instalación aparte)
- **Node.js 18+** → https://nodejs.org
- **pnpm** (gestor de paquetes del frontend):
  ```bash
  npm install -g pnpm
  ```
- **Git Bash** (en Windows) — recomendado para evitar problemas de permisos con PowerShell

---

## 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/gestion-repuestos.git
cd gestion-repuestos
```

---

## 2. Levantar el Backend (Spring Boot)

```bash
cd backend
```

Revisa `src/main/resources/application.properties` y configura la conexión a tu base de datos local (PostgreSQL/Supabase, según corresponda).

```bash
./mvnw spring-boot:run
```

> En Windows con PowerShell, si `./mvnw` falla, usa `mvnw.cmd spring-boot:run`, o mejor usa Git Bash.

El backend debería quedar disponible en `http://localhost:8080`.

⚠️ **Nota:** mientras la base de datos no esté configurada, el backend puede no arrancar o el frontend mostrará errores de conexión (`Failed to fetch`) — esto es esperado y no bloquea ver la interfaz del frontend.

---

## 3. Levantar el Frontend (React + Vite)

Abre **otra terminal** (deja el backend corriendo en la primera):

```bash
cd frontend
```

### 3.1. Crea tu archivo de variables de entorno

Copia la plantilla incluida:

```bash
cp .env.example .env
```

### 3.2. Instala las dependencias

```bash
pnpm install
```

### 3.3. Levanta el servidor de desarrollo

**En Git Bash (Windows), usa este comando** (evita que Bash interprete mal la ruta base):

```bash
export MSYS_NO_PATHCONV=1
pnpm dev
```

**En Mac/Linux o CMD/PowerShell**, simplemente:

```bash
pnpm dev
```

El frontend quedará disponible en:

```
http://localhost:5173/
```

---

## Notas importantes para el equipo

- **No subir el archivo `.env`** al repositorio — cada quien crea el suyo localmente a partir de `.env.example`.
- El proyecto usa **pnpm**, no npm ni yarn — si usas otro gestor, el `package.json` (con dependencias `catalog:`) puede fallar.
- Los plugins `@replit/vite-plugin-cartographer` y `@replit/vite-plugin-dev-banner` fueron removidos porque solo funcionan dentro del entorno de Replit y no son necesarios para desarrollo local.
- Si el frontend carga pero las peticiones a la API fallan, es porque el backend y/o la base de datos aún no están completamente configurados — no es un error del frontend.

---

## Scripts útiles

### Backend
```bash
./mvnw spring-boot:run    # Levanta el servidor
./mvnw clean install      # Compila y corre tests
```

### Frontend
```bash
pnpm dev        # Servidor de desarrollo
pnpm build      # Build de producción
pnpm preview    # Previsualiza el build de producción
```
