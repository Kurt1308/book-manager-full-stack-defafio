
# 📚 Book Manager

Aplicação Full-Stack para gerenciamento de livros desenvolvida como desafio técnico.

## Funcionalidades

- Cadastro de usuários
- Login com autenticação JWT
- CRUD completo de livros
- Busca por título
- Paginação
- Proteção de rotas privadas
- Documentação da API com Swagger/OpenAPI

## Tecnologias

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
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

## Estrutura do Projeto

```text
book-manager/
├── backend/
├── frontend/
├── README.md
└── schema.sql
```

## Pré-requisitos

- Java 21
- Maven
- Node.js 22+
- PostgreSQL

## Configuração

### Backend

Copie:

```bash
cp backend/.env.example backend/.env
```

### Frontend

Copie:

```bash
cp frontend/.env.example frontend/.env
```

No Windows, basta renomear `.env.example` para `.env`.

## Banco de Dados

Crie um banco chamado `bookmanager`.

O arquivo `schema.sql` será executado automaticamente na inicialização da aplicação.

## Executando o Backend

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui/index.html

## Executando o Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend:

http://localhost:5173

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

- AuthServiceTest
- BookServiceTest
- BookControllerTest

## Telas

Adicione as imagens da pasta `Docs`:

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

## Execução com Docker (Opcional)

```bash
docker compose up --build
```

## Melhorias Futuras

- Refresh Token
- Upload de capa
- Recuperação de senha
- CI/CD
- Deploy em nuvem

## Autor

**Lucas Dias**

GitHub: https://github.com/Kurt1308

Repositório:

https://github.com/Kurt1308/book-manager-full-stack-defafio
