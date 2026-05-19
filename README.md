# SuplesStore

Proyecto Full Stack desarrollado con arquitectura de microservicios utilizando Spring Boot y Spring Cloud.

## Descripción

SuplesStore es una tienda online de suplementos deportivos diseñada bajo una arquitectura de microservicios. El proyecto permite gestionar autenticación de usuarios, perfiles, productos, inventario, carrito de compras, órdenes, pagos y reseñas.

El sistema utiliza un API Gateway para centralizar el acceso a los servicios y Eureka Server para el descubrimiento dinámico de microservicios.

---

# Arquitectura del Proyecto

El proyecto está compuesto por los siguientes microservicios:

| Microservicio      | Descripción                            |
| ------------------ | -------------------------------------- |
| eureka-service     | Registro y descubrimiento de servicios |
| api-gateway        | Punto de entrada principal del sistema |
| auth-service       | Autenticación y generación de JWT      |
| perfil-service     | Gestión de perfiles de usuario         |
| producto-service   | Administración de productos            |
| inventario-service | Control de stock                       |
| carrito-service    | Gestión del carrito de compras         |
| orden-service      | Gestión de órdenes y compras           |
| pago-service       | Procesamiento de pagos                 |
| resena-service     | Gestión de reseñas y comentarios       |

---

# Tecnologías Utilizadas

## Backend

* Java 17
* Spring Boot
* Spring Cloud
* Spring Security
* JWT Authentication
* BCrypt
* Maven
* Eureka Server
* API Gateway
* JPA / Hibernate
* MySQL

## Herramientas

* Postman
* IntelliJ IDEA
* Git
* GitHub
* DBDiagram

---

# Funcionalidades Principales

## Autenticación

* Registro de usuarios
* Inicio de sesión
* Encriptación de contraseñas con BCrypt
* Generación y validación de JWT
* Protección de endpoints

## Gestión de Perfil

* Creación de perfiles
* Actualización de datos personales
* Relación única entre usuario y perfil
* Eliminación lógica

## Productos e Inventario

* CRUD de productos
* Control de stock
* Gestión de inventario
* Productos precargados

## Carrito y Órdenes

* Agregar productos al carrito
* Generar órdenes
* Gestión de compras
* Cálculo de totales

## Pagos

* Registro de pagos
* Asociación de pagos con órdenes

## Reseñas

* Crear reseñas de productos
* Asociar reseñas a usuarios

---

# Arquitectura Implementada

El proyecto utiliza una arquitectura basada en microservicios para separar responsabilidades y mejorar:

* Escalabilidad
* Mantenibilidad
* Seguridad
* Independencia entre servicios
* Facilidad de despliegue

Además, se implementó:

* Service Discovery con Eureka
* API Gateway centralizado
* Seguridad con JWT
* Comunicación mediante REST APIs

---

# Base de Datos

El proyecto utiliza MySQL.

El script principal para crear las bases de datos es:

```sql
crear_bases_suplestore.sql
```

---

# Ejecución del Proyecto

## 1. Clonar repositorio

```bash
git clone https://github.com/TU-USUARIO/SuplesStore.git
```

---

## 2. Configurar MySQL

Crear las bases de datos utilizando:

```sql
crear_bases_suplestore.sql
```

---

## 3. Levantar Eureka Server

```bash
cd eureka-service
./mvnw spring-boot:run
```

---

## 4. Levantar API Gateway

```bash
cd api-gateway
./mvnw spring-boot:run
```

---

## 5. Levantar microservicios

Ejecutar cada servicio:

```bash
cd nombre-servicio
./mvnw spring-boot:run
```

---

# Endpoints Principales

## Auth

```http
POST /auth/register
POST /auth/login
```

## Perfil

```http
POST /api/perfiles
GET /api/perfiles
```

## Productos

```http
POST /api/productos
GET /api/productos
```

## Carrito

```http
POST /api/carrito
GET /api/carrito
```

## Orden

```http
POST /api/ordenes
GET /api/ordenes
```

---

# Seguridad

El sistema implementa:

* JWT para autenticación
* BCrypt para contraseñas
* Spring Security
* Protección de endpoints
* Validaciones con DTOs y @Valid

---

# Estructura del Proyecto

```text
SuplesStore/
│
├── eureka-service/
├── api-gateway/
├── auth-service/
├── perfil-service/
├── producto-service/
├── inventario-service/
├── carrito-service/
├── orden-service/
├── pago-service/
├── resena-service/
│
├── crear_bases_suplestore.sql
├── diagrama/
└── presentacion/
```

---

# Integrantes

* Bastian Alarcon
* Cesar Adasme
* Ignacio Hidalgo

---

# Asignatura

Full Stack I

Profesor:

Christian Eduardo

---

# Estado del Proyecto

Proyecto académico funcional desarrollado como evaluación final para la asignatura Full Stack I.

Actualmente incluye:

* Arquitectura de microservicios
* API Gateway
* Eureka Server
* Seguridad JWT
* CRUDs funcionales
* Persistencia con MySQL
* Comunicación REST

---

# Autor

Proyecto desarrollado por estudiantes de Ingeniería en Informática.
