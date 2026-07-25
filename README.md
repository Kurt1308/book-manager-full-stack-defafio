# 📚 Book Manager - Full Stack

Aplicação full-stack para gerenciamento de livros, desenvolvida como desafio técnico.

O sistema permite que usuários criem uma conta, realizem autenticação utilizando JWT e gerenciem sua biblioteca de livros através de operações CRUD.

---

# 🚀 Tecnologias utilizadas

## Backend

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI

## Frontend

- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia
- Axios
- Bootstrap 5

## Infraestrutura

- Docker
- Docker Compose
- PostgreSQL Containerizado
- Nginx

---

# 📂 Estrutura do projeto

```
BookManager
│
├── backend
│   ├── src
│   │   └── main
│   │       └── resources
│   │           └── schema.sql
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend
│   ├── src
│   ├── Dockerfile
│   ├── package.json
│   └── vite.config.ts
│
├── docker-compose.yml
│
└── README.md
```

---

# ✨ Funcionalidades implementadas

## Autenticação

- Cadastro de usuário
- Login utilizando JWT
- Armazenamento do token no frontend
- Proteção das rotas internas

## Gerenciamento de livros

- Listagem de livros
- Busca por título
- Cadastro de livros
- Edição de livros
- Exclusão de livros
- Consulta por Título

---

# 🔐 Autenticação JWT

A aplicação utiliza autenticação baseada em JWT.

Fluxo:

1. Usuário realiza login
2. Backend valida as credenciais
3. Backend retorna o token JWT
4. Frontend armazena o token
5. As próximas requisições enviam:

```
Authorization: Bearer TOKEN
```

---

# 🗄️ Banco de dados

Banco utilizado:

```
PostgreSQL
```

A estrutura das tabelas está versionada em:

```
backend/src/main/resources/schema.sql
```

Tabelas principais:

- users
- books

Relacionamento:

```
Usuário 1 -------- N Livro
```

Cada livro pertence ao usuário autenticado.

---

# ▶️ Execução local (sem Docker)

## Pré-requisitos

Instalar:

- Java 21
- Maven
- Node.js
- PostgreSQL


---

# Configuração do Banco

Criar um banco PostgreSQL:

```
bookmanager
```

Configurar usuário e senha no arquivo:

```
backend/src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookmanager
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
```

---

# Executando o Backend

Acesse:

```bash
cd backend
```

Instale as dependências:

```bash
mvn clean install
```

Execute:

```bash
mvn spring-boot:run
```

Backend disponível:

```
http://localhost:8080
```

Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

# Executando o Frontend

Acesse:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute:

```bash
npm run dev
```

Frontend disponível:

```
http://localhost:5173
```

---

# Variáveis de ambiente Frontend

Arquivo:

```
frontend/.env
```

Conteúdo:

```env
VITE_API_URL=http://localhost:8080
```

---

# 🐳 Execução utilizando Docker

## Pré-requisitos

Instalar:

- Docker Desktop

---

# Construindo as imagens

Na raiz do projeto:

```bash
cd BookManager
```

Executar:

```bash
docker compose build
```

---

# Subindo os containers

Executar:

```bash
docker compose up
```

Ou em segundo plano:

```bash
docker compose up -d
```

---

# Containers criados

## PostgreSQL

Container:

```
bookmanager-postgres
```

Porta:

```
5432
```

---

## Backend

Container:

```
bookmanager-backend
```

Porta:

```
8080
```

---

## Frontend

Container:

```
bookmanager-frontend
```

Porta:

```
5173
```

---

# Variáveis de ambiente Docker

Arquivo:

```
frontend/.env.production
```

Conteúdo:

```env
VITE_API_URL=http://backend:8080
```

---

# Acessos

Frontend:

```
http://localhost:5173
```

Backend:

```
http://localhost:8080
```

Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

# Parando os containers

```bash
docker compose down
```

Removendo containers e volumes:

```bash
docker compose down -v
```

---

# 📌 Endpoints principais

## Authentication

Registrar usuário:

```
POST /auth/register
```

Login:

```
POST /auth/login
```

---

# Books

Listar livros:

```
GET /books
```

Busca por título:

```
GET /books?title=java
```

Criar livro:

```
POST /books
```

Buscar por ID:

```
GET /books/{id}
```

Atualizar:

```
PUT /books/{id}
```

Excluir:

```
DELETE /books/{id}
```

---

# 📄 Modelo Book

| Campo | Tipo | Obrigatório |
|-|-|-|
| title | String | Sim |
| author | String | Sim |
| year | Integer | Não |
| description | String | Não |

---

# 🏗️ Arquitetura

## Backend

Arquitetura em camadas:

```
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Separação utilizando:

- Entities
- DTOs
- Services
- Repositories
- Controllers
- Security Configuration

---

## Frontend

Estrutura:

```
src

├── api
├── components
├── router
├── services
├── stores
├── types
├── views
└── assets
```

---

# 🧪 Testes realizados

- Cadastro de usuário
- Login JWT
- Proteção de rotas
- CRUD completo de livros
- Busca por título
- Persistência PostgreSQL
- Execução local
- Execução completa via Docker Compose

---

# 👨‍💻 Autor

Lucas Dias

GitHub:

```
https://github.com/Kurt1308
```

---

# 📌 Repositório

Projeto:

```
https://github.com/Kurt1308/book-manager-full-stack-defafio.git
```