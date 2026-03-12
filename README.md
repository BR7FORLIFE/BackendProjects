# Chat con IA y Kafka usando Spring Ecosystem

## 🔹 Descripción del proyecto

Este proyecto implementa un sistema de **chat inteligente** utilizando:

- **Spring WebFlux** para manejar clientes WebSocket de manera reactiva.  
- **Modelos de Inteligencia Artificial** para generar respuestas automáticas (IA generativa, LLMs).  
- **Apache Kafka** como **sistema de mensajería de eventos** para desacoplar la comunicación entre backend y servicio de IA.

El flujo principal del sistema es:


Esto permite:

- Escalabilidad en la gestión de mensajes.  
- Procesamiento asíncrono y desacoplado.  
- Integración fácil con múltiples modelos de IA.  

---

## 🔹 Tecnologías principales

| Área | Tecnologías |
|------|-------------|
| Backend | Spring WebFlux, Spring Boot |
| Mensajería | Apache Kafka, Docker Compose |
| IA | Integración con LLMs (Ollama u otros modelos) |
| Contenedores | Docker, Docker Compose |
| Lenguaje | Java (backend), JavaScript (cliente WebSocket) |

---

- **Topics de Kafka**: `chat-requests` y `chat-responses`.  
- **Particiones**: 3 por defecto, ajustables según carga.  
- **Consumer groups**: permiten escalar el servicio de IA.

---

## 🔹 Requisitos

- Docker & Docker Compose  
- Java 17+  
- Maven o Gradle  
- Node.js (para cliente WebSocket opcional)  

---

## 🔹 Levantar el proyecto (Desarrollo)

1. Levantar Kafka usando Docker Compose:

```bash
docker compose up -d

docker exec -it kafka kafka-topics.sh --create --topic chat-requests --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics.sh --create --topic chat-responses --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

spring:
  kafka:
    bootstrap-servers: localhost:9092

./mvnw spring-boot:run

```

Integración con IA

El AI Service puede conectarse a cualquier modelo compatible con API de LLM (Ollama, OpenAI, etc).

Los mensajes se envían mediante Kafka topics para desacoplar la comunicación.

El backend escucha el topic de respuestas y entrega la respuesta al cliente WebSocket.

🔹 Buenas prácticas

Crear topics manualmente para producción.

Ajustar num.partitions y replication.factor según la escala.

Configurar listener PLAINTEXT para desarrollo y SSL/SASL para producción.

Usar consumer groups para balancear carga de IA.

🔹 Extensiones futuras

Integración con múltiples modelos de IA simultáneamente.

Monitoreo de Kafka con Kafka UI, Prometheus y Grafana.

Persistencia y almacenamiento de logs de chat.

Escalabilidad horizontal del backend y del AI worker.