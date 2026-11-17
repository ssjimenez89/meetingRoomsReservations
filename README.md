"# meetingRoomsReservations" 

# Documentación de la API

Esta aplicación tiene como objetivo reservar salas de reuniones.

# Url

http://localhost:8082


# UrlSwagger

http://localhost:8082/swagger-ui/index.html

# Tecnologías
### Spring Boot 4.1.2
### H2 Database
### Java 17
### Maven

# Para ejecutar la App

mvn spring-boot:run

En caso de algún problema, realizar un mvn clean y volver a intentar.

# Seguridad

Se implementó una seguridad básica, que se puede utilizar accediendo con las siguientes credenciales:

## Usuario: admin
## Password: admin

Se tuvo en cuenta que para listar en cualquiera de los endpoints no sea necesario estar autenticado.
Para los crear, así para el cancelar Reserva si será necesaria la autenticación.

# Entidades de la App

### Sala (nombre, capacidad, disponibilidad, reservas)
### Reserva (sala, responsable, inicio, fin, nombreActividad)
### Responsable (nombre, apellidos, edad, sexo, cargo)

Se definió que Sala tiene una relación de uno a muchos con Reserva y 
Reserva tiene una relación de uno a uno con Responsable.


# Requisitos de la App

-Crear salas
-Listar salas

-Crear reservas
-Listar reservas
-Cancelar reservas

-Crear Reesponsables
-Listar Responsables

# Listar responsables
## Ejemplo de request (GET /api/listar/responsables)

## Response body

```json
[
  {
    "id": 1,
    "nombre": "Pablo",
    "apellidos": "Diaz",
    "edad": 25,
    "sexo": "M",
    "cargo": "Empleado"
  },
  {
    "id": 2,
    "nombre": "Juan",
    "apellidos": "Perez",
    "edad": 35,
    "sexo": "M",
    "cargo": "Director"
  }
]

```

# Crear responsable
## Ejemplo de request (POST /api/responsables)

## Body 

```json
{
  
  "nombre": "Pablo",
  "apellidos": "Diaz",
  "edad": 25,
  "sexo": "M",
  "cargo": "Empleado"
}
```
	
## Response body

```json
{
  "id": 1,
  "nombre": "Pablo",
  "apellidos": "Diaz",
  "edad": 25,
  "sexo": "M",
  "cargo": "Empleado"
}
```

# Listar salas
## Ejemplo de request (GET /api/listar/salas)

## Response body

```json

[
  {
    "id": 1,
    "nombre": "Salon de Conferencia",
    "capacidad": 50,
    "disponibilidad": true,
    "reservas": []
  }
]

```

# Crear sala
## Ejemplo de request (POST /api/salas')

## Body 

```json
{  
  "nombre": "Salon de Conferencia",
  "capacidad": 50,
  "disponibilidad": true,
  "reservas": []
}
```
	
## Response body

```json

{
  "id": 1,
  "nombre": "Salon de Conferencia",
  "capacidad": 50,
  "disponibilidad": true,
  "reservas": []
}
```

# Listar reservas
## Ejemplo de request (GET /api/listar/reservas)

## Response body

```json

[
  {
    "id": 1,
    "salaId": 1,
    "responsableId": 1,
    "inicio": "2026-11-17T18:12:17.49",
    "fin": "2026-11-17T18:12:17.49",
    "nombreActividad": "Reunion de fin de mes",
    "estado": "ACTIVA"
  }
]

```

# Crear Reserva
## Ejemplo de request (POST /api/reservas')

## Body 

```json
{ 
  "salaId": 1,
  "responsableId": 1,
  "inicio": "2026-11-17T18:12:17.490Z",
  "fin": "2026-11-17T18:12:17.490Z",
  "nombreActividad": "Reunion de fin de mes"
}
```
	
## Response body

```json

{
  "id": 1,
  "salaId": 1,
  "responsableId": 1,
  "inicio": "2026-11-17T18:12:17.49",
  "fin": "2026-11-17T18:12:17.49",
  "nombreActividad": "Reunion de fin de mes",
  "estado": "ACTIVA"
}
```

# Cancelar Reserva
## Ejemplo de request (PUT api/reservas/1)

## Body 

```json
{
  "id": 1,
  "salaId": 1,
  "responsableId": 1,
  "inicio": "2026-11-17T18:12:17.49",
  "fin": "2026-11-17T18:12:17.49",
  "nombreActividad": "Reunion de fin de mes",
  "estado": "ACTIVA"
}
```

## Response body

```json

{
  "id": 1,
  "salaId": 1,
  "responsableId": 1,
  "inicio": "2026-11-17T18:12:17.49",
  "fin": "2026-11-17T18:12:17.49",
  "nombreActividad": "Reunion de fin de mes",
  "estado": "CANCELADA"
}
```


# Listar reservas por sala con paginado
## Ejemplo de request (GET /api/listar/paging/reservas/sala?id=1&nombre=Salon%20de%20Conferencia&capacidad=50&disponibilidad=true&reservas=%5B%5D&page=0&size=5)

## Response body

```json

{
  "totalItems": 2,
  "reservas": [
    {
      "id": 1,
      "salaId": 1,
      "responsableId": 1,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "CANCELADA"
    },
    {
      "id": 2,
      "salaId": 1,
      "responsableId": 2,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "ACTIVA"
    }
  ],
  "totalPages": 1,
  "currentPage": 0
}

```

# Listar reservas por inicio con paginado
## Ejemplo de request (GET /api/listar/paging/reservas/inicio?inicio=2026-11-17T18%3A12%3A17.490Z&page=0&size=1)

## Response body

```json

{
  "totalItems": 2,
  "reservas": [
    {
      "id": 1,
      "salaId": 1,
      "responsableId": 1,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "CANCELADA"
    }
  ],
  "totalPages": 2,
  "currentPage": 0
}

```

# Listar reservas por fin con paginado
## Ejemplo de request (GET /api/listar/paging/reservas/fin?fin=2026-11-17T18%3A12%3A17.490Z&page=0&size=2)

## Response body

```json

{
  "totalItems": 2,
  "reservas": [
    {
      "id": 1,
      "salaId": 1,
      "responsableId": 1,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "CANCELADA"
    },
    {
      "id": 2,
      "salaId": 1,
      "responsableId": 2,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "ACTIVA"
    }
  ],
  "totalPages": 1,
  "currentPage": 0
}

```

# Listar reservas por fecha (inicio y fin) con paginado
## Ejemplo de request (GET /api/listar/paging/reservas/fecha?from=2026-11-17T18%3A12%3A17.490Z&to=2026-11-17T18%3A12%3A17.490Z&page=0&size=3)

## Response body

```json

{
  "totalItems": 2,
  "reservas": [
    {
      "id": 1,
      "salaId": 1,
      "responsableId": 1,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "CANCELADA"
    },
    {
      "id": 2,
      "salaId": 1,
      "responsableId": 2,
      "inicio": "2026-11-17T18:12:17.49",
      "fin": "2026-11-17T18:12:17.49",
      "nombreActividad": "Reunion de fin de mes",
      "estado": "ACTIVA"
    }
  ],
  "totalPages": 1,
  "currentPage": 0
}

```

# Criterios que se tuvieron en cuenta del negocio

Si se intenta crear una Reserva con inicio o fin, que estén contenido o tome parte del horario de una Reserva que ya existe,
se devuelve 409 y un mensaje especificando que "La Sala esta ocupada en este horario".

Asi mismo se validaron errores 400, para cuado se envian campos nulos en una reserva.

Teniendo enn cuenta la relaciónn de uno a uno entre las Entides de Reserva y Responsable también se validó que no se
pueda crear dos Reservas con el mismo Responsable, para lo cual se lanza el error 400 con una alerta especificando que "El responsable ya tiene un Reserva".

# Que haría con más Tiempo?

Mejoraría la Seguridad, con tokens o una versión más elaborada de Seguridad Básica.
Completaría los tests.
Refactorizaría algunas clases y métodos para limpiar el código.
Le agregaría un frontend.
Le añadiría las migraciones en Flyway o Liquibase.

# Cuánto tiempo me llevó?

Me llevó más de 3 horas implementar la App, aproveche las 48 horas para revisar todo lo que debía tener en cuenta 
según las especificaciones que me enviaron, pero siempre en el momento de implementar se presentan imprevistos que pueden
demorar un poco más el desarrollo, utilicé la IA para crear las Entities, Las DTOs, los Mappers, los tests, el resto lo implementé directamente para tener en cuenta lo especificado.










