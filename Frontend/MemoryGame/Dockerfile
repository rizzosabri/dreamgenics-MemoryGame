# Utiliza una imagen oficial de Node.js 18 como imagen base
FROM node:18 as build

# Establece el directorio de trabajo
WORKDIR /usr/src/app

# Copia el archivo package.json y package-lock.json al directorio de trabajo
COPY package*.json ./

# Instala las dependencias del proyecto
RUN npm install

# Copia todo el proyecto al directorio de trabajo
COPY . .

# Compila la aplicación Angular
RUN npm run build --prod

# Utiliza una imagen ligera de nginx para servir la aplicación
FROM nginx:alpine

# Copia los archivos compilados de la etapa de build a la carpeta de nginx
COPY --from=build /usr/src/app/dist/memory-game /usr/share/nginx/html

# Expone el puerto 80
EXPOSE 80

# Comando para ejecutar nginx
CMD ["nginx", "-g", "daemon off;"]
