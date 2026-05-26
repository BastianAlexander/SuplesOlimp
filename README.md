# SuplesOlimp

Proyecto académico Full Stack desarrollado con arquitectura de microservicios utilizando Spring Boot, Spring Cloud, Eureka Server, API Gateway, OpenFeign, Flyway y MySQL.

## Descripción

SuplesOlimp es una tienda online de suplementos deportivos. El sistema permite gestionar usuarios, perfiles de clientes, productos, categorías, inventario, carrito de compras, órdenes, pagos y reseñas.

En la versión actual, el proyecto **no utiliza JWT ni roles**. El `auth-service` maneja un registro e inicio de sesión básico con correo y contraseña, y el `perfil-service` asocia los datos personales del cliente con el usuario registrado.

---

## Arquitectura del Proyecto

El proyecto está compuesto por los siguientes microservicios:

| Microservicio | Descripción |
|---|---|
| `eureka-service` | Registro y descubrimiento de microservicios |
| `config-server` | Servidor centralizado de configuración |
| `api-gateway` | Punto de entrada principal del sistema |
| `auth-service` | Registro, login y gestión básica de usuarios |
| `perfil-service` | Gestión de perfiles de clientes |
| `producto-service` | Gestión de productos y categorías |
| `inventario-service` | Control de stock por producto |
| `carrito-service` | Gestión de carritos e items del carrito |
| `orden-service` | Creación de órdenes desde carrito |
| `pago-service` | Registro de pagos y cambio de estado de la orden |
| `resena-service` | Gestión de reseñas de productos |

---

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Hibernate
- OpenFeign
- Eureka Server
- API Gateway
- Config Server
- Flyway
- MySQL
- Maven
- Lombok
- Postman
- Git y GitHub
- dbdiagram.io

---

## Funcionalidades Principales

### Auth Service

- Registro de usuarios.
- Inicio de sesión básico con correo y contraseña.
- Listado de usuarios.
- Búsqueda de usuario por ID.
- Búsqueda de usuario por correo.
- Actualización de correo y contraseña validando la contraseña actual.
- Consulta de fecha de creación de cuenta.
- Eliminación de usuario.

### Perfil Service

- CRUD de perfiles.
- Asociación de perfil con usuario de `auth-service`.
- Consulta de perfil por ID.
- Consulta de perfil por `authId`.
- Consulta de perfil mediante correo del usuario.
- Consulta de última modificación.
- Comunicación con `auth-service` mediante OpenFeign.

### Producto Service

- CRUD de productos.
- CRUD de categorías.
- Relación lógica entre producto y categoría.
- DTO plano para mostrar producto con `idCategoria` y `nombreCategoria`.
- Buscar productos por nombre.
- Buscar productos por categoría.
- Buscar productos por rango de precio.

### Inventario Service

- CRUD de inventario.
- Asociación lógica con producto mediante `productoId`.
- Visualización de datos del producto mediante OpenFeign.
- Buscar inventario por producto.
- Aumentar stock.
- Disminuir stock.
- Validación para evitar stock negativo.

### Carrito Service

- CRUD de carritos.
- CRUD de items del carrito.
- Asociación del carrito con cliente.
- Asociación de items con productos.
- Visualización de cliente mediante OpenFeign.
- Visualización de producto mediante OpenFeign.
- Buscar carritos por cliente.
- Buscar items por carrito.
- Vaciar carrito.

### Orden Service

- Listar órdenes.
- Buscar orden por ID.
- Crear orden desde carrito.
- Calcular total de la orden.
- Crear items de la orden.
- Descontar stock desde `inventario-service`.
- Vaciar carrito desde `carrito-service`.
- Evitar crear dos órdenes desde el mismo carrito.
- Buscar órdenes por cliente.
- Buscar órdenes por estado.
- Cambiar estado de una orden.

### Pago Service

- Listar pagos.
- Buscar pago por ID.
- Pagar una orden.
- Validar que el monto ingresado coincida con el total de la orden.
- Registrar pago como `APROBADO`.
- Cambiar automáticamente la orden a estado `PAGADA`.
- Buscar pagos por orden.
- Buscar pagos por estado.
- Buscar pagos por método de pago.

### Reseña Service

- CRUD de reseñas.
- Asociación lógica con producto.
- Asociación lógica con cliente.
- Visualización de producto mediante OpenFeign.
- Visualización de cliente mediante OpenFeign.
- Buscar reseñas por producto.
- Buscar reseñas por cliente.
- Buscar reseñas por calificación.
- Calcular promedio de calificación por producto.
- Reseñas precargadas mediante Flyway.

---

## Flujo Principal de Compra

```text
Cliente registrado
      ↓
Perfil de cliente
      ↓
Carrito
      ↓
Items del carrito
      ↓
Orden creada desde carrito
      ↓
Descuento de stock en inventario
      ↓
Vaciado del carrito
      ↓
Pago de orden
      ↓
Orden cambia a PAGADA
      ↓
Cliente puede crear reseña
```

---

## Bases de Datos

Cada microservicio utiliza su propia base de datos MySQL.

| Microservicio | Base de datos |
|---|---|
| `auth-service` | `bd_auth_service` |
| `perfil-service` | `bd_perfil_service` |
| `producto-service` | `bd_productos_service` |
| `inventario-service` | `bd_inventario_service` |
| `carrito-service` | `bd_carrito_service` |
| `orden-service` | `bd_orden_service` |
| `pago-service` | `bd_pago_service` |
| `resena-service` | `bd_resena_service` |

Las tablas se crean mediante migraciones Flyway ubicadas en:

```text
src/main/resources/db/migration
```

---

## Modelo de Datos Principal

El sistema maneja las siguientes tablas principales:

```text
usuarios_auth
perfil
categoria
producto
inventario
carrito
carrito_item
orden
orden_item
pago
resena
```

El modelo actual no utiliza tablas de roles.

---

## Orden recomendado para levantar el sistema

```text
1. eureka-service
2. config-server
3. auth-service
4. perfil-service
5. producto-service
6. inventario-service
7. carrito-service
8. orden-service
9. pago-service
10. resena-service
11. api-gateway
```

---

## Ejecución del Proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/BastianAlexander/SuplesOlimp.git
```

### 2. Crear bases de datos

Ejecutar el script SQL de creación de bases de datos ubicado en:

```text
database/crear_bases_suplestore.sql
```

### 3. Levantar Eureka Server

```bash
cd eureka-service
./mvnw spring-boot:run
```

### 4. Levantar Config Server

```bash
cd config-server
./mvnw spring-boot:run
```

### 5. Levantar microservicios

```bash
cd nombre-servicio
./mvnw spring-boot:run
```

### 6. Levantar API Gateway

```bash
cd api-gateway
./mvnw spring-boot:run
```

---

## Endpoints Principales

### Auth

```http
GET    /api/v1/auth
GET    /api/v1/auth/{id}
POST   /api/v1/auth
PUT    /api/v1/auth/{id}
DELETE /api/v1/auth/{id}

POST /api/v1/auth/login
GET  /api/v1/auth/{id}/fecha-creacion
GET  /api/v1/auth/correo/{correo}
```

### Perfil

```http
GET    /api/v1/perfil
GET    /api/v1/perfil/{id}
POST   /api/v1/perfil
PUT    /api/v1/perfil/{id}
DELETE /api/v1/perfil/{id}

GET /api/v1/perfil/auth/{authId}
GET /api/v1/perfil/ultima-modificacion/{id}
GET /api/v1/perfil/correo/{correo}
```

### Producto

```http
GET    /api/v1/producto
GET    /api/v1/producto/{id}
POST   /api/v1/producto
PUT    /api/v1/producto/{id}
DELETE /api/v1/producto/{id}

GET /api/v1/producto/buscar/{nombre}
GET /api/v1/producto/categoria/{idCategoria}
GET /api/v1/producto/precio/{precioMin}/{precioMax}
```

### Categoría

```http
GET    /api/v1/categoria
GET    /api/v1/categoria/{id}
POST   /api/v1/categoria
PUT    /api/v1/categoria/{id}
DELETE /api/v1/categoria/{id}
```

### Inventario

```http
GET    /api/v1/inventario
GET    /api/v1/inventario/{id}
POST   /api/v1/inventario
PUT    /api/v1/inventario/{id}
DELETE /api/v1/inventario/{id}

GET /api/v1/inventario/producto/{productoId}
PUT /api/v1/inventario/{id}/aumentar/{cantidad}
PUT /api/v1/inventario/{id}/disminuir/{cantidad}
```

### Carrito

```http
GET    /api/v1/carrito
GET    /api/v1/carrito/{id}
POST   /api/v1/carrito
PUT    /api/v1/carrito/{id}
DELETE /api/v1/carrito/{id}

GET /api/v1/carrito/cliente/{clienteId}
```

### Carrito Item

```http
GET    /api/v1/carrito-item
GET    /api/v1/carrito-item/{id}
POST   /api/v1/carrito-item
PUT    /api/v1/carrito-item/{id}
DELETE /api/v1/carrito-item/{id}

GET    /api/v1/carrito-item/carrito/{carritoId}
DELETE /api/v1/carrito-item/carrito/{carritoId}/vaciar
```

### Orden

```http
GET    /api/v1/orden
GET    /api/v1/orden/{id}
DELETE /api/v1/orden/{id}

POST /api/v1/orden/crear-desde-carrito/{carritoId}

GET /api/v1/orden/cliente/{clienteId}
GET /api/v1/orden/estado/{estado}
PUT /api/v1/orden/{id}/estado/{estado}
```

### Pago

```http
GET    /api/v1/pago
GET    /api/v1/pago/{id}
DELETE /api/v1/pago/{id}

POST /api/v1/pago/pagar-orden/{ordenId}

GET /api/v1/pago/orden/{ordenId}
GET /api/v1/pago/estado/{estado}
GET /api/v1/pago/metodo/{metodoPago}
```

### Reseña

```http
GET    /api/v1/resena
GET    /api/v1/resena/{id}
POST   /api/v1/resena
PUT    /api/v1/resena/{id}
DELETE /api/v1/resena/{id}

GET /api/v1/resena/producto/{productoId}
GET /api/v1/resena/cliente/{clienteId}
GET /api/v1/resena/calificacion/{calificacion}
GET /api/v1/resena/producto/{productoId}/promedio
```

---

## Ejemplo de Flujo en Postman

### Crear usuario

```http
POST http://localhost:8080/api/v1/auth
```

```json
{
  "correo": "cliente@gmail.com",
  "password": "123456"
}
```

### Login

```http
POST http://localhost:8080/api/v1/auth/login
```

```json
{
  "correo": "cliente@gmail.com",
  "password": "123456"
}
```

### Crear carrito

```http
POST http://localhost:8080/api/v1/carrito
```

```json
{
  "clienteId": 1
}
```

### Agregar producto al carrito

```http
POST http://localhost:8080/api/v1/carrito-item
```

```json
{
  "carritoId": 1,
  "productoId": 1,
  "cantidad": 2
}
```

### Crear orden desde carrito

```http
POST http://localhost:8080/api/v1/orden/crear-desde-carrito/1
```

### Pagar orden

```http
POST http://localhost:8080/api/v1/pago/pagar-orden/1
```

```json
{
  "monto": 99980,
  "metodoPago": "TARJETA"
}
```

### Crear reseña

```http
POST http://localhost:8080/api/v1/resena
```

```json
{
  "productoId": 1,
  "clienteId": 1,
  "comentario": "Muy buen producto, recomendado",
  "calificacion": 5
}
```

---

## Estado del Proyecto

Proyecto académico funcional desarrollado como evaluación final para la asignatura Full Stack I.

Actualmente incluye:

- Arquitectura de microservicios.
- Eureka Server.
- Config Server.
- API Gateway.
- Bases de datos separadas por microservicio.
- Migraciones con Flyway.
- DTOs y mappers.
- Comunicación entre microservicios mediante OpenFeign.
- CRUDs funcionales.
- Endpoints personalizados por microservicio.
- Flujo completo de compra.
- Pago de orden.
- Reseñas de productos.
- Modelo lógico en dbdiagram.io.

---

## Integrantes

- Bastian Alarcon
- Cesar Adasme
- Ignacio Hidalgo

---

## Asignatura

Full Stack I

Profesor: Christian Eduardo

---

## Autor

Proyecto desarrollado por estudiantes de Ingeniería en Informática.
