# ==========================================
# Etapa de Build
# ==========================================
FROM node:22-alpine AS build

WORKDIR /app

# Copia os arquivos de dependências
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o restante do projeto
COPY . .

# Gera a versão de produção
RUN npm run build

# ==========================================
# Etapa de Execução
# ==========================================
FROM nginx:alpine

# Remove a página padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos gerados pelo Vite
COPY --from=build /app/dist /usr/share/nginx/html

# Expõe a porta HTTP
EXPOSE 80

# Inicializa o Nginx
CMD ["nginx", "-g", "daemon off;"]