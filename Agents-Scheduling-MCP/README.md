# Spring Agent Studio

> Un agente de IA construido con Spring Boot y Spring AI cuyo objetivo no es ser un chatbot, sino una plataforma para aprender el ecosistema moderno de Spring mediante herramientas, observabilidad y el protocolo MCP.

---

# ¿Qué es este proyecto?

Spring Agent Studio es un proyecto educativo diseñado para aprender **Spring Boot** desde una perspectiva diferente al típico CRUD o API REST.

En lugar de construir una aplicación web, el proyecto implementa un **agente inteligente** que interactúa con el usuario mediante una **interfaz de consola**, similar a herramientas como:

- Claude Code
- Gemini CLI
- OpenAI Codex CLI
- Aider

El agente puede utilizar diferentes herramientas para resolver tareas reales.

Por ejemplo:

```
> ¿Qué archivos Java modificaste esta semana?

Analizando repositorio...

✓ Encontré 7 archivos modificados.

Resumen:

- AgentService.java
- GitTool.java
- ToolRegistry.java
...
```

o

```
> Resume los errores del log de hoy.

Leyendo logs...

Analizando con el modelo...

Resumen generado.
```

El objetivo principal **no es el agente**, sino utilizarlo como excusa para recorrer una gran parte del ecosistema moderno de Spring.

---

# Filosofía del proyecto

Este proyecto intenta responder una pregunta:

> ¿Cómo aprender Spring más allá de hacer otro CRUD?

En lugar de desarrollar un sistema de usuarios, productos o pedidos, el agente va incorporando capacidades mediante componentes desacoplados.

Cada nueva funcionalidad representa un concepto del framework.

Por ejemplo:

- agregar una herramienta nueva
- incorporar observabilidad
- integrar MCP
- registrar métricas
- cachear respuestas
- ejecutar tareas programadas

Cada módulo nuevo enseña una característica diferente de Spring.

---

# Objetivos

## Objetivo principal

Aprender Spring Boot moderno construyendo un agente extensible.

---

## Objetivos secundarios

- Aprender Spring AI
- Comprender la arquitectura Tool Calling
- Aprender el protocolo MCP
- Utilizar Actuator
- Incorporar Observabilidad
- Aprender Micrometer
- Implementar eventos
- Aprender Scheduling
- Implementar Cache
- Incorporar Retry
- Experimentar con Async
- Utilizar Docker Compose Support
- Diseñar una arquitectura limpia basada en componentes

---

# Tecnologías

- Java 21+
- Spring Boot 3.x
- Spring AI
- Spring AI MCP
- Spring Boot Actuator
- Micrometer
- Docker Compose Support
- Maven
- Ollama (desarrollo local)
- PostgreSQL (opcional)
- Redis (opcional)

---

# Arquitectura

```
                    Usuario
                       │
                       ▼
              Console Interface
                       │
                       ▼
                Agent Controller
                       │
                       ▼
                 Spring AI Agent
                       │
         ┌─────────────┼──────────────┐
         │             │              │
         ▼             ▼              ▼
    Tool Registry   Memory      Observability
         │
         │
         ├───────────────┐
         │               │
         ▼               ▼
     Local Tools     MCP Tools
```

---

# Flujo de ejecución

```
Usuario escribe un comando

↓

La consola recibe el mensaje

↓

El agente analiza la petición

↓

El modelo decide si necesita herramientas

↓

Se ejecutan las herramientas

↓

Se devuelve el resultado

↓

Se registran métricas

↓

Se almacenan eventos

↓

Se muestran resultados al usuario
```

---

# Funcionalidades

## Agente

- Conversación
- Memoria
- Tool Calling
- Respuestas estructuradas
- Planificación de tareas

---

## Interfaz de consola

Inspirada en herramientas modernas como Claude Code.

Ejemplo:

```
──────────────────────────────────────────

Spring Agent Studio

──────────────────────────────────────────

>

```

Comandos disponibles:

```
help

tools

clear

exit

history

metrics
```

---

# Herramientas

Cada herramienta representa una capacidad del agente.

Todas implementan una interfaz común.

```
Tool

↓

Filesystem Tool

↓

Git Tool

↓

Database Tool

↓

Weather Tool

↓

Documentation Tool

↓

Shell Tool

↓

MCP Tool
```

---

## Filesystem Tool

Permite trabajar con archivos.

Capacidades:

- listar directorios
- leer archivos
- buscar texto
- buscar archivos
- obtener estadísticas

Ejemplos

```
Busca todos los README del proyecto.

Lee el pom.xml

Resume el archivo Application.java
```

---

## Git Tool

Interacción con Git.

Capacidades

- status
- log
- diff
- branches
- commits
- historial

Ejemplos

```
¿Qué cambió hoy?

Resume el último commit.

¿Cuántos commits hice esta semana?
```

---

## Database Tool

Consulta bases de datos.

Puede responder preguntas como

```
¿Cuántos usuarios existen?

¿Cuáles son las tablas?

¿Cuánto ocupa la base?
```

---

## Weather Tool

Consulta información meteorológica.

Ejemplo

```
¿Cómo estará el clima mañana?
```

---

## Documentation Tool

Busca documentación técnica.

Puede consultar

- Spring
- Java
- Maven
- Docker

Ejemplo

```
Explícame @Transactional.
```

---

## Shell Tool

Ejecuta comandos permitidos.

Ejemplos

```
pwd

ls

whoami
```

Se ejecutarán únicamente comandos seguros.

---

## MCP Tools

El agente puede descubrir herramientas externas utilizando MCP.

No necesita conocerlas previamente.

Ejemplo

```
Spring Agent

↓

Servidor MCP GitHub

↓

Servidor MCP Filesystem

↓

Servidor MCP Browser

↓

Servidor MCP PostgreSQL
```

---

# Observabilidad

Uno de los objetivos principales del proyecto.

El agente registra todo lo que ocurre.

## Métricas

- cantidad de consultas
- tiempo de respuesta
- herramientas utilizadas
- llamadas al LLM
- errores
- consumo de tokens

---

## Actuator

Se utilizará para exponer información del sistema.

Ejemplos

```
/health

/metrics

/info

/beans

/configprops
```

---

## Micrometer

Se crearán métricas propias.

Ejemplos

```
agent.requests

tool.executions

tool.errors

llm.calls

tokens.used

conversation.length
```

---

# Eventos

El sistema estará basado en eventos.

Ejemplos

```
PromptReceivedEvent

↓

ToolExecutionStarted

↓

ToolExecutionFinished

↓

ResponseGenerated

↓

ConversationFinished
```

Esto permite desacoplar completamente los componentes.

---

# Cache

Algunas herramientas podrán cachear respuestas.

Ejemplos

- documentación
- clima
- consultas repetidas

---

# Retry

Las llamadas que puedan fallar tendrán reintentos automáticos.

Ejemplos

- APIs externas
- llamadas al modelo
- conexiones MCP

---

# Scheduling

El agente podrá ejecutar tareas periódicas.

Ejemplos

- resumir logs
- limpiar caché
- revisar repositorios
- generar reportes

---

# Arquitectura de paquetes

```
com.agentstudio

├── agent
│
├── chat
│
├── config
│
├── console
│
├── events
│
├── memory
│
├── metrics
│
├── mcp
│
├── models
│
├── scheduler
│
├── services
│
├── tools
│   ├── filesystem
│   ├── git
│   ├── database
│   ├── weather
│   ├── shell
│   └── documentation
│
├── observability
│
└── utils
```

---

# Roadmap

## Fase 1

- [ ] Proyecto base
- [ ] Consola interactiva
- [ ] Spring AI
- [ ] Ollama

---

## Fase 2

- [ ] Tool Registry
- [ ] Filesystem Tool
- [ ] Git Tool
- [ ] Weather Tool

---

## Fase 3

- [ ] Base de datos
- [ ] Cache
- [ ] Retry
- [ ] Async

---

## Fase 4

- [ ] Spring AI MCP
- [ ] Conexión a servidores MCP
- [ ] Descubrimiento dinámico de herramientas

---

## Fase 5

- [ ] Actuator
- [ ] Micrometer
- [ ] Dashboard de métricas

---

## Fase 6

- [ ] Memoria conversacional
- [ ] Planificación
- [ ] Eventos
- [ ] Automatizaciones

---

# Principios de diseño

- Componentes desacoplados
- Arquitectura extensible
- Bajo acoplamiento
- Alta cohesión
- Observabilidad desde el inicio
- Configuración antes que código
- Aprender haciendo

---

# Lo que quiero aprender

Este proyecto busca explorar gran parte del ecosistema de Spring moderno.

- Spring Boot
- Spring AI
- Spring AI MCP
- Dependency Injection
- Auto Configuration
- Bean Lifecycle
- Events
- Scheduling
- Validation
- Cache
- Retry
- Async
- Actuator
- Micrometer
- Observability
- Docker Compose Support
- Configuration Properties

---

# Estado

🚧 Proyecto en desarrollo.

Cada commit agregará una nueva capacidad al agente y, al mismo tiempo, explorará un nuevo módulo del ecosistema Spring.

---

# Licencia

Proyecto desarrollado con fines educativos para experimentar con Spring Boot, Spring AI y el protocolo MCP.