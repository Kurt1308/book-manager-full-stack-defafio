# 📚 Book Manager

Aplicação Full-Stack para gerenciamento de livros desenvolvida como desafio técnico.

## Funcionalidades

- Cadastro de usuários
- Login com JWT
- CRUD completo de livros
- Busca por título
- Paginação
- Proteção de rotas
- Swagger/OpenAPI
- Docker e Docker Compose

## Tecnologias

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Maven

### Frontend
- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia
- Axios
- Bootstrap 5

## Estrutura

```
book-manager/
├── backend/
├── frontend/
├── docker-compose.yml
└── README.md
```

## Pré-requisitos

- Java 21
- Node.js 22+
- Maven
- Docker e Docker Compose (recomendado)

## Configuração

Copie os arquivos de exemplo:

Backend

```
cp backend/.env.example backend/.env
```

Frontend

```
cp frontend/.env.example frontend/.env
```

No Windows, basta renomear `.env.example` para `.env`.

## Executando com Docker

```
docker compose up --build
```

A aplicação ficará disponível em:

- Frontend: http://localhost:5173
- Backend: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html

O banco PostgreSQL é criado automaticamente pelo Docker através da variável `POSTGRES_DB`, não sendo necessário executar `CREATE DATABASE`.

## Execução Local

Backend:

```
cd backend
mvn clean install
mvn spring-boot:run
```

Frontend:

```
cd frontend
npm install
npm run dev
```

## Endpoints

### Autenticação

- POST /auth/register
- POST /auth/login

### Livros

- GET /books
- GET /books/{id}
- POST /books
- PUT /books/{id}
- DELETE /books/{id}

## Testes

O backend possui testes automatizados para:

- AuthService
- BookService
- BookController

## Telas

- Login
![alt text](Docs/login.png)
- Cadastro
![alt text](Docs/register.png)
- Home
![alt text](Docs/home.png)
- Lista de Livros
![alt text](Docs/books-list.png)
- Cadastro/Edição de Livro
![alt text](Docs/book-form.png)
- Swagger
![alt text](Docs/swagger.png)

## Melhorias futuras

- Refresh Token
- Recuperação de senha
- Upload de capa
- Testes de frontend
- CI/CD
- Deploy em nuvem

## Autor

**Lucas Dias**

GitHub: https://github.com/Kurt1308

Repositório:

https://github.com/Kurt1308/book-manager-full-stack-defafio
