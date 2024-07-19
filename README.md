# dreamgenics-MemoryGame

# Proyecto Full-Stack

Este proyecto incluye dos componentes principales:

- **Frontend:** Aplicación Angular
- **Backend:** API Spring Boot

## Instrucciones de Ejecución

Para ejecutar el proyecto, sigue los siguientes pasos:

1. **Clonar el Repositorio**

2. **Ejecutar el Backend con Docker**

    ```bash
    cd Backend
    docker-compose up
    ```

    Esto levantará los servicios definidos en el archivo `docker-compose.yml`. Asegúrate de tener Docker y Docker Compose instalados en tu máquina.

3. **Acceder a la Aplicación**

    Una vez que los servicios estén en funcionamiento, puedes acceder a la aplicación Angular en tu navegador en la siguiente URL:

    [http://localhost:4200](http://localhost:4200)


## Estructura del Proyecto

- **`frontend/`**: Contiene el código fuente de la aplicación Angular.
- **`backend/`**: Contiene el código fuente de la API Spring Boot y el archivo `docker-compose.yml`.

## Requisitos

- Docker
- Docker Compose
- Si utilizas linux; agrega a docker-compose:     platform: linux/x86_64


## Notas Adicionales

documentación de la api: Backend/dreamgenics-api/README.md`.

