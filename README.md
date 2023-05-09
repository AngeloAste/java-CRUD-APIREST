# Documentación de la API

## Introducción

Esta es la documentación del Servicio API REST Java (Java 11)(Spring Initializr  y IntelliJ IDEA)  JWT authentication Framework Spring Boot y Gradle,   
en el cual se crean los endpoints correspondientes a un CRUD de usuarios comunicandose con una base de datos en memoria H2.

La API CRUD de usuarios permite crear, leer, actualizar y eliminar usuarios, así como también generar tokens de autenticación.

## Autenticación

Para realizar operaciones en la API es necesario incluir un token de autenticación en el encabezado de la solicitud HTTP. El token se puede generar llamando a la API de autenticación, enviando el correo electrónico y la contraseña del usuario. El token tiene una duración limitada y debe renovarse periódicamente.

### Generación del token de autenticación
La generación del token de autenticación se realiza mediante una solicitud POST a la siguiente URL:

**/auth**

### Generalmente se usa  http://localhost:8080/auth
** En la pestaña "Headers"  debe establecerse El encabezado Content-Type  y Value   application/json**
![paso-0](https://user-images.githubusercontent.com/110312875/236974093-f68eacda-686d-44ad-a1f8-88dfa1313833.png)

**Usar siguiente body**

**{
    "username": "javainuse",
    "password": "password"
}**


![paso-1](https://user-images.githubusercontent.com/110312875/236974214-8aa5905a-a445-43d1-b846-8cca52beb67b.png)

La respuesta será un objeto JSON con el campo token que contiene el token de autenticación.

### Uso del token de autenticación

Para realizar operaciones en la API se debe incluir el token de autenticación en el encabezado de la solicitud HTTP con la siguiente estructura:

**Authorization: Bearer {token}**


 
## Endpoints
### Para cada Endpoints usar Authorization: Bearer {token} donde el token fue obtenido por/authenticate
### Además se debe establecer el encabezado Content-Type  y Value application/json

### Los Endpoints de la API son los siguientes:

### **POST /api/users**: Crea un nuevo usuario.
### **GET /api/users/{id}**: Obtiene la información de un usuario por su ID.
### **GET /api/users**: Obtiene la información de todos los usuarios.
### **PUT /api/users/{id}**: Actualiza la información de un usuario por su ID.
### **DELETE /api/users/{id}**: Elimina un usuario por su ID.

 ## Operaciones CRUD de usuarios

### Creación de usuarios

Para crear un usuario se debe realizar una solicitud **POST** a la siguiente URL:

**/api/users**

![Paso-5](https://user-images.githubusercontent.com/110312875/236975061-bb4347b2-9e80-4b61-bf26-f68d21cb47e8.png)

El cuerpo de la solicitud debe contener un objeto JSON con los campos **email, name, password y phones**. El campo **phones** debe ser una lista de objetos JSON con el campo **number**.

En caso de registrar correctamente la respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Obtención de un usuario
Para obtener un usuario se debe realizar una solicitud **GET** a la siguiente URL:

**/api/users/{id}**

![Paso-4](https://user-images.githubusercontent.com/110312875/236974674-4744e102-eee8-4958-9e45-4118ce5c623c.png)

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea obtener.

La respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Actualización de un usuario
Para actualizar un usuario se debe realizar una solicitud **PUT** a la siguiente URL:

**/api/users/{id}**

![paso-7-PUT](https://user-images.githubusercontent.com/110312875/236974588-9886195d-b928-4b92-b1d5-0cc122d285cf.png)

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea actualizar.

El cuerpo de la solicitud debe contener un objeto JSON con los campos **email, name, password y phones**. El campo **phones** debe ser una lista de objetos JSON con el campo **number**.

En caso de actualizar correctamente la respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Eliminación de un usuario

Para eliminar un usuario se debe realizar una solicitud **DELETE** a la siguiente URL:

**/api/users/{id}**

![paso-8-DELETE](https://user-images.githubusercontent.com/110312875/236974614-83cf04e2-4ec7-42a6-8947-a00f2dbcdd36.png)

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea eliminar.

La respuesta será un objeto JSON con el campo **message** indicando que el usuario fue eliminado correctamente.

