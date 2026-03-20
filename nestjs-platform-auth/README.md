# 🚀 Auth Platform + API Gateway (NestJS)

## 📌 Overview

Plataforma de autenticación y autorización basada en microservicios construida con NestJS.
El objetivo del proyecto es replicar, a pequeña escala, las capacidades de sistemas como Auth0 o Spring Security + Gateway.

Este sistema permite:

* Autenticación con JWT (access + refresh tokens)
* Autorización basada en roles (RBAC)
* API Gateway centralizado
* Comunicación entre microservicios
* Seguridad avanzada (rotación de tokens, claves RSA, rate limiting)

---

## 🧠 Architecture

El sistema sigue una arquitectura de microservicios:

```
                ┌───────────────┐
                │   Client App  │
                └──────┬────────┘
                       │
                       ▼
                ┌───────────────┐
                │  API Gateway  │
                └──────┬────────┘
        ┌──────────────┼──────────────┐
        ▼              ▼              ▼
┌────────────┐ ┌────────────┐ ┌────────────┐
│ Auth       │ │ Users      │ │ Billing    │
│ Service    │ │ Service    │ │ Service    │
└────────────┘ └────────────┘ └────────────┘
        │
        ▼
   ┌──────────┐
   │  Redis   │
   └──────────┘
        │
        ▼
   ┌──────────┐
   │ Database │
   └──────────┘
```

---

## 🧱 Tech Stack

* NestJS
* Node.js
* TypeScript
* Prisma ORM
* PostgreSQL
* Redis
* Docker
* JWT + Passport
* JOSE (JWK, JWE, JWS)

---

## 📦 Services

### 🔐 Auth Service

Responsable de la autenticación y emisión de tokens.

Features:

* Login / Register
* Access Token (short-lived)
* Refresh Token (rotation + blacklist)
* Firma con claves RSA (JWK)
* Revocación de sesiones

---

### 👤 Users Service

Gestión de usuarios y roles.

Features:

* CRUD de usuarios (mínimo)
* Roles y permisos (RBAC)
* Validación de identidad

---

### 💳 Billing Service (Mock)

Simula lógica de pagos.

Features:

* Endpoints protegidos
* Validación de permisos
* Simulación de suscripciones

---

### 🚪 API Gateway

Punto de entrada del sistema.

Features:

* Validación de JWT
* Routing hacia microservicios
* Rate limiting
* Logging

---

## 🔐 Authentication Flow

1. Usuario hace login → Auth Service
2. Se generan:

   * Access Token (expira rápido)
   * Refresh Token (persistido en Redis)
3. Cliente usa Access Token para acceder a APIs
4. Cuando expira:

   * usa Refresh Token
   * se genera uno nuevo (rotación)

---

## 🔑 Security Features

* JWT firmado con RSA (no HMAC)
* Rotación de refresh tokens
* Blacklist en Redis
* Rate limiting
* Guards (NestJS)
* Validación centralizada en Gateway

---

## 📁 Project Structure

```
apps/
  api-gateway/
  auth-service/
  users-service/
  billing-service/

libs/
  common/
  auth/
  config/
```

---

## ⚙️ Setup

### 1. Clonar repositorio

```bash
git clone https://github.com/your-username/auth-platform
cd auth-platform
```

### 2. Instalar dependencias

```bash
npm install
```

### 3. Variables de entorno

Crear `.env` en cada servicio:

```
DATABASE_URL=postgresql://user:password@localhost:5432/db
JWT_PRIVATE_KEY=...
JWT_PUBLIC_KEY=...
REDIS_HOST=localhost
```

---

### 4. Levantar servicios (Docker)

```bash
docker-compose up -d
```

---

### 5. Ejecutar proyecto

```bash
npm run start:dev
```

---

## 🧪 Testing

```bash
npm run test
```

---

## 📡 Example Endpoints

### Auth

```
POST /auth/login
POST /auth/register
POST /auth/refresh
POST /auth/logout
```

### Users

```
GET /users
GET /users/:id
```

### Billing

```
GET /billing/subscription
```

---

## 🚀 Roadmap

* [ ] Multi-tenant support
* [ ] OAuth2 integration (Google, GitHub)
* [ ] Audit logs
* [ ] API versioning
* [ ] GraphQL gateway
* [ ] Kubernetes deployment

---

## 🎯 Goals of the Project

Este proyecto está diseñado para:

* Aprender NestJS a nivel profesional
* Entender arquitectura de microservicios en Node.js
* Implementar seguridad avanzada
* Replicar patrones de sistemas enterprise

---

## 🧠 Key Learnings

* Dependency Injection en NestJS
* Guards vs Middleware
* Diseño de APIs seguras
* Gestión de tokens JWT avanzada
* Comunicación entre servicios

---

### BR7FORLIFE