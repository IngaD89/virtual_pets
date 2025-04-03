# Virtual Pets

Virtual Pets es una aplicación web que permite a los usuarios crear y gestionar mascotas virtuales inspiradas en los personajes de Minions. Cada mascota tiene estados que cambian con el tiempo, y los usuarios pueden interactuar con ellas para mantenerlas saludables y felices.

## Tecnologías utilizadas

- **Frontend:** React (JSX, CSS tradicional)
- **Backend:** Java con Spring Boot
- **Base de datos:** MySql
- **Autenticación:** Spring Security, Token JWT 
- **WebSockets:** Para la actualización en tiempo real de los estados de las mascotas
- **Documentación:** Swagger para la documentación de la API

## Características principales

### 1. Autenticación y Autorización
- Inicio de sesión y registro de usuarios.
- Uso de JWT para proteger rutas.
- Diferenciación entre usuarios básicos y administradores.

### 2. Gestor de Mascotas
- Creación de mascotas seleccionando un personaje y asignándoles un nombre.
- Visualización de una lista de mascotas en la página principal.
- Visualización de los detalles de cada mascota.

### 3. Estados de las Mascotas
- Las mascotas tienen diferentes estados de energía y hambre.
- Un servicio en el backend actualiza estos estados cada 60 segundos.
- Cuando la energía es baja o el hambre es alta, la mascota cambia de tipo (amarillo o morado).

### 4. Conexión en Tiempo Real
- WebSocket permite que los cambios en el estado de las mascotas se reflejen en el frontend sin necesidad de refrescar la página.
- Los WebSockets requieren autenticación para garantizar que solo los usuarios autorizados reciban actualizaciones en tiempo real.

### 5. Documentación de la API
- La API está documentada con Swagger, lo que permite a los desarrolladores explorar y probar los endpoints de manera interactiva.
- Para acceder a la documentación, una vez que el backend esté en ejecución, visitar:
  ```
  http://localhost:8080/swagger-ui/index.html
  ```

## Instalación y Configuración
### Requisitos
- Node.js y npm
- Java 21
- MySql

### Configuración del Backend
1. Clonar el repositorio.
2. Configurar la base de datos en `application.properties`.
3. Ejecutar la aplicación con:
   ```sh
   mvn spring-boot:run
   ```

### Configuración del Frontend
1. Navegar a la carpeta del frontend.
2. Instalar dependencias:
   ```sh
   npm install
   ```
3. Iniciar el servidor de desarrollo:
   ```sh
   npm start
   ```

## Reflexiones sobre el Proyecto
Este proyecto fue desarrollado utilizando IA para generar parte del frontend. Durante el proceso, se analizaron los códigos generados y se ajustaron según las necesidades del proyecto. Se exploraron conceptos como el manejo de estados en React, la autenticación con JWT y la comunicación en tiempo real con WebSockets.

## Contribuciones
Las contribuciones son bienvenidas. Si deseas mejorar el proyecto, por favor realiza un fork del repositorio y envía un pull request.



