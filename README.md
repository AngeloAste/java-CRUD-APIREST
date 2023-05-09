# Documentación de la API

## Introducción

Esta es la documentación de la API de ejemplo para el CRUD de usuarios. Esta API fue construida utilizando el framework Spring Boot y se comunica con una base de datos en memoria H2. 
La API CRUD de usuarios permite crear, leer, actualizar y eliminar usuarios, así como también generar tokens de autenticación.

## Autenticación

Para realizar operaciones en la API es necesario incluir un token de autenticación en el encabezado de la solicitud HTTP. El token se puede generar llamando a la API de autenticación, enviando el correo electrónico y la contraseña del usuario. El token tiene una duración limitada y debe renovarse periódicamente.

### Generación del token de autenticación
La generación del token de autenticación se realiza mediante una solicitud POST a la siguiente URL:

**/auth**
 
El cuerpo de la solicitud debe contener un objeto JSON con los campos **email** y **password**.

### Usar siguiente body
**{
    "username": "javainuse",
    "password": "password"
}**

La respuesta será un objeto JSON con el campo token que contiene el token de autenticación.


### Uso del token de autenticación

Para realizar operaciones en la API se debe incluir el token de autenticación en el encabezado de la solicitud HTTP con la siguiente estructura:

**Authorization: Bearer {token}**


 
## Endpoints
### Los Endpoints de la API son los siguientes:

**POST /api/users**: Crea un nuevo usuario.
**GET /api/users/{id}**: Obtiene la información de un usuario por su ID.
**GET /api/users**: Obtiene la información de todos los usuarios.
**PUT /api/users/{id}**: Actualiza la información de un usuario por su ID.
**DELETE /api/users/{id}**: Elimina un usuario por su ID.

 ## Operaciones CRUD de usuarios

### Creación de usuarios

Para crear un usuario se debe realizar una solicitud **POST** a la siguiente URL:

**/api/users**

El cuerpo de la solicitud debe contener un objeto JSON con los campos **email, name, password y phones**. El campo **phones** debe ser una lista de objetos JSON con el campo **number**.

La respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Obtención de un usuario
Para obtener un usuario se debe realizar una solicitud **GET** a la siguiente URL:

**/api/users/{id}**

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea obtener.

La respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Actualización de un usuario
Para actualizar un usuario se debe realizar una solicitud **PUT** a la siguiente URL:

**/api/users/{id}**

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea actualizar.

El cuerpo de la solicitud debe contener un objeto JSON con los campos **email, name, password y phones**. El campo **phones** debe ser una lista de objetos JSON con el campo **number**.

La respuesta será un objeto JSON con los campos **id, created, modified y token**.

### Eliminación de un usuario

Para eliminar un usuario se debe realizar una solicitud **DELETE** a la siguiente URL:

**/api/users/{id}**

El campo **{id}** debe ser reemplazado por el ID del usuario que se desea eliminar.

La respuesta será un objeto JSON con el campo **message** indicando que el usuario fue eliminado correctamente.

