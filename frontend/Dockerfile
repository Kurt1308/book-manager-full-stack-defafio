# ============================
# Build da aplicação Vue
# ============================
FROM node:22-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

# ============================
# Servidor Web (Nginx)
# ============================

FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

ENTRYPOINT [
    "nginx",
    "-g",
    "daemon off;"
]