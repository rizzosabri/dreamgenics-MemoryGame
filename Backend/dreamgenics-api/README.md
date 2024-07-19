# API DREAMGENICS MEMORY GAME


## Endpoints

### Obtener todos los Scores

#### GET `/api/scores`

**Descripción:** Devuelve una lista de los 10 mejores scores de la base de datos, ordenados según cantidad de movimientos.

**Respuesta Exitosa:**
- **Código:** 200 OK.
- **Cuerpo:** Array de Objetos Score

- Ejemplo de Respuesta:
   ```json
   [
       {
          "id": 1,
          "name": "post del Jugador",
          "team": "Nombre del Equipo",
          "moves": 100,
          "durationSecs": 120
      }
   ]
    ```

### Crear un Nuevo Score

#### POST `/api/scores`

**Descripción:** Guarda un Score en la base de datos.

**Cuerpo de la Solicitud:**

- **Tipo:** JSON
- **Ejemplo:**
    ```json
   {
          "name": "post del Jugador",
          "team": "Nombre del Equipo",
          "moves": 100,
          "durationSecs": 120
      }
    ```

**Respuesta Exitosa:**

- **Código:** 200 OK
- **Cuerpo:** Objeto Score

#### DELETE `/api/scores/{id}`

**Descripción:** Elimina un score por su ID.

**Parámetros de Ruta:**

- **Nombre:** `id`
- **Tipo:** Long

**Respuesta Exitosa:**

- **Código:** 204 No Content

### Actualizar un Score

#### PUT `/api/scores/{id}`

**Descripción:** Actualiza un score existente por su ID.

**Parámetros de Ruta:**

- **Nombre:** `id`
- **Tipo:** Long

**Cuerpo de la Solicitud:**

- **Tipo:** JSON
- **Ejemplo:**
    ```json
    {
        "name": "nuevo nombre",
        "team": "nuevo equipo",
        "moves": 150,
        "durationSecs": 300
    }
    ```

**Respuesta Exitosa:**

- **Código:** 200 OK
- **Cuerpo:** Objeto `Score`

**Respuesta de Error:**

- **Código:** 404 Not Found

### Obtener Imagen por Consulta

#### GET `/api/imgBackSide`

**Descripción:** Devuelve la URL de una imagen basada en una consulta de búsqueda.

**Parámetros de Consulta:**

- **Nombre:** `query`
- **Tipo:** String

**Respuesta Exitosa:**

- **Código:** 200 OK
- **Cuerpo:** URL de la imagen en formato String

- **Ejemplo de Respuesta:**
    ```json
    "https://example.com/path/to/image.jpg"
    ```

**Respuesta de Error:**

- **Código:** 404 Not Found
- **Cuerpo:** Mensaje de error

- **Ejemplo de Respuesta:**
    ```json
    "Image not found"
    ```