# Mini Reddit Realtime

> Plataforma tipo Reddit construida para practicar arquitectura de microservicios moderna.

---

## Objetivos técnicos

- Microservicios independientes
- WebSockets y comunicación realtime
- Rate limiting y protección anti-spam
- Caché distribuida con Redis
- Colas asíncronas con BullMQ y RabbitMQ
- Auth con JWT y refresh tokens
- Upload de archivos con validación
- Notificaciones en tiempo real

---

## Stack

| Capa | Tecnologías |
|------|-------------|
| **Backend** | Java + Spring Boot, NestJS |
| **Base de datos** | PostgreSQL, Redis |
| **Mensajería** | Kafka, RabbitMQ |
| **Frontend** | Next.js, TailwindCSS |
| **Infra** | Docker, Docker Compose |

---

## Arquitectura

El sistema está dividido en microservicios pequeños e independientes.

```js
root/
├── auth-service/
├── chat-service/
├── notification-service/
├── api-gateway/
├── frontend/
├── docker-compose.yml
└── README.md
```

### Servicios

#### 🔐 Auth Service — Spring Boot · Puerto `8081`

Responsable de registro, login, JWT, refresh tokens, roles/permisos y validación de usuarios.

**Stack:** Spring Security · JWT · PostgreSQL

---

#### 💬 Chat Service — Spring Boot · Puerto `8082`

Responsable de comentarios realtime, WebSockets, feed de comentarios, likes y respuestas.

**Stack:** Spring WebSocket · Redis Pub/Sub · PostgreSQL

---

#### 🔔 Notification Service — NestJS · Puerto `3001`

Responsable de notificaciones realtime, emails mock, eventos del sistema y procesamiento async.

**Stack:** NestJS · BullMQ · Redis

---

#### 🌐 API Gateway — NestJS · Puerto `3000`

Responsable de centralizar requests, rate limiting, auth middleware y routing interno.

**Stack:** NestJS · Redis

---

## Comunicación entre servicios

Comunicación híbrida según el tipo de operación:

**HTTP** — para requests síncronos, validación de auth y consultas simples.

**RabbitMQ** — para eventos, notificaciones y procesamiento async.

### Eventos del sistema

```js
user.created
post.created
comment.created
notification.send
```

---

## Funcionalidades

### Auth
- Registro y login
- JWT access token + refresh token
- Hash de passwords

### Feed
- Crear posts · Ver feed · Likes · Comentarios

### Realtime
- Comentarios en tiempo real via WebSockets
- Notificaciones automáticas al recibir comentarios, likes o respuestas

### Uploads
- Imágenes y avatares
- Validación de tamaño y metadata

### Redis
- Sesiones
- Feed cacheado
- Rate limiting
- Eventos realtime

### Rate Limiting
- Intentos de login
- Spam de comentarios y posts

### Colas (BullMQ + RabbitMQ)
- Envío de notificaciones
- Procesamiento async
- Tareas en background

---

## Flujo básico

```txt
Usuario inicia sesión
Auth Service genera JWT
Usuario crea un post
Chat Service guarda el post
Evento publicado en RabbitMQ
Notification Service procesa el evento
Usuarios suscritos reciben notificación realtime
```

---

## Variables de entorno

**Auth Service**
```env
JWT_SECRET=
DB_URL=
REDIS_HOST=
```

**Notification Service**
```env
REDIS_HOST=
RABBITMQ_URL=
```

**Chat Service**
```env
DB_URL=
REDIS_HOST=
```

---

## Cómo ejecutar

```bash
docker compose up --build
```

---

## Posibles mejoras

- [ ] Kubernetes
- [ ] CI/CD pipeline
- [ ] Observabilidad con OpenTelemetry
- [ ] Elasticsearch para búsqueda
- [ ] Sistema de followers
- [ ] Trending posts
- [ ] CDN para uploads