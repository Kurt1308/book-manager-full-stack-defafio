# 📚 Book Manager - Full Stack

Aplicação full-stack para gerenciamento de livros, desenvolvida como desafio técnico.

O sistema permite que usuários realizem cadastro, autenticação segura utilizando JWT e gerenciamento completo de uma biblioteca pessoal de livros através de operações CRUD.

A aplicação foi desenvolvida utilizando uma arquitetura separada entre backend e frontend, utilizando boas práticas de desenvolvimento, autenticação stateless, banco de dados PostgreSQL, containerização com Docker e documentação da API através do Swagger/OpenAPI.

---

# 🚀 Tecnologias utilizadas

## Backend

- Java 21
- Spring Boot 3.5.3
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL 18
- Maven
- Swagger / OpenAPI
- Lombok
- Bean Validation


## Frontend

- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia
- Axios
- Bootstrap 5
- SweetAlert2


## Infraestrutura

- Docker
- Docker Compose
- Nginx
- PostgreSQL Containerizado


---

# ✨ Funcionalidades implementadas

## 🔐 Autenticação e segurança

- Cadastro de usuários
- Login utilizando JWT
- Geração de token autenticado pelo backend
- Armazenamento do token no frontend
- Proteção das rotas privadas
- Controle de sessão através do JWT
- Logout manual
- Logout automático após expiração do token


## 📚 Gerenciamento de livros

- Cadastro de livros
- Listagem paginada
- Busca por título
- Consulta por ID
- Atualização de livros
- Exclusão de livros
- Associação dos livros ao usuário autenticado


## ✅ Validações

Frontend:

- Validação de campos obrigatórios
- Controle de formulário
- Mensagens de retorno amigáveis
- Tratamento de erros da API
- Controle de carregamento durante requisições


Backend:

- Validação dos dados recebidos
- Controle de autenticação
- Proteção dos endpoints
- Tratamento de exceções


---

# 🔒 Segurança JWT

A aplicação utiliza autenticação baseada em **JSON Web Token (JWT)**.

O fluxo de autenticação funciona da seguinte forma:

Usuário
|
v
Realiza login
|
v
Backend valida credenciais
|
v
Backend gera JWT
|
v
Frontend armazena token
|
v
Requisições futuras enviam:

Autorização: Bearer Token


## Implementações de segurança:

- Token Stateless
- Expiração configurável
- Secret configurado através de variável de ambiente
- Token contendo apenas informações necessárias
- Subject utilizando o ID do usuário
- Nome do usuário armazenado como claim
- Senhas protegidas utilizando BCrypt
- Filtro JWT utilizando Spring Security
- Interceptor Axios para envio automático do token
- Remoção automática do token inválido no frontend


---

# 🏗️ Arquitetura da aplicação

O projeto foi desenvolvido utilizando separação de responsabilidades.

## Backend

Arquitetura em camadas:

Controlador
|
v
Serviço
|
v
Repositório
|
v
Banco de Dados



Responsabilidades:

### Controller

Responsável por:

- Receber requisições HTTP
- Validar entradas
- Retornar respostas


### Service

Responsável por:

- Regras de negócio
- Processamento dos dados
- Comunicação entre Controller e Repository


### Repository

Responsável por:

- Comunicação com banco de dados
- Operações utilizando JPA/Hibernate


### DTO

Utilizado para:

- Separação entre entidade e dados expostos pela API
- Controle dos dados de entrada e saída


---

# Frontend

Arquitetura baseada em componentes Vue 3:

Src
|
v
Componentes
|
v
Serviços
|
v
Axios API
|
v
API REST de backend



Responsabilidades:

### Views

Responsáveis pelas páginas da aplicação:

- Login
- Cadastro
- Home
- Livros


### Components

Componentes reutilizáveis:

- Navbar
- Formulários
- Elementos visuais


### Services

Responsáveis pela comunicação com backend:

- Autenticação
- Livros
- Alertas


### Stores

Gerenciamento de estado:

- Usuário autenticado
- Token JWT
- Dados da sessão


### Utils

Funções auxiliares:

- Validação de token
- Funções compartilhadas


---

# 📂 Estrutura do projeto


Docs
│
├── login.png
├── register.png
├── home.png
├── books-list.png
├── book-form.png
├── swagger.png


---

## Tela de Login

Descrição:

> Tela inicial da aplicação exibindo o formulário de autenticação com campos de e-mail e senha, botão de login e opção para cadastro de novo usuário.


![Tela de Login](Docs/login.png)


Tela de Cadastro

Descrição:

Tela de criação de usuário contendo nome, e-mail, senha e botão de cadastro.


![Tela de Login](Docs/register.png)


Tela Home

Descrição:

Tela principal após autenticação mostrando a navegação da aplicação, acesso ao gerenciamento de livros e usuário autenticado.

Inserir:

![Tela de Login](Docs/home.png)

---

## Tela de Gerenciamento de Livros

Descrição:

Tela responsável pelo gerenciamento da biblioteca pessoal do usuário autenticado.

Funcionalidades apresentadas:

- Visualização dos livros cadastrados
- Busca por título
- Acesso à edição
- Exclusão de livros
- Botão para cadastro de novos livros


![Tela de Login](Docs/books-list.png)



---

## Tela de Cadastro de Livro

Descrição:

Formulário utilizado para adicionar um novo livro à biblioteca do usuário.

Campos disponíveis:

- Título
- Autor
- Ano de publicação
- Descrição


Cadastro de livro:

![Tela de Login](Docs/book-form.png)



---

## Documentação Swagger / OpenAPI

Descrição:

A API disponibiliza documentação interativa utilizando Swagger/OpenAPI.

Através desta interface é possível:

- Visualizar endpoints disponíveis
- Testar requisições HTTP
- Validar respostas da API
- Testar autenticação JWT utilizando Bearer Token


Inserir:

![Tela de Login](Docs/swagger.png)



---

# 🗄️ Banco de dados

O projeto utiliza PostgreSQL como banco de dados principal.

## Estrutura principal

Banco de dados: bookmanager

users

id
name
email
password

books

id
title
author
year
description
user_id


Relacionamento:



Usuário

1

|

N

Livro



Cada usuário possui sua própria biblioteca de livros.


---

# ⚙️ Configuração do ambiente


## Variáveis de ambiente Backend


Arquivo:



backend/.env



Exemplo:


```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bookmanager

SPRING_DATASOURCE_USERNAME=postgres

SPRING_DATASOURCE_PASSWORD=SUA_SENHA  (Senha usada na configuração do banco de dados)


JWT_SECRET=SUA_CHAVE_JWT_SEGURA (Configure uma chave segura, no meu projeto usei:

JWT_SECRET=BookManagerJWTSecretKey2026ProductionSecureAuthenticationToken256Bits

)

JWT_EXPIRATION=300000


APP_SWAGGER_LAUNCH=true


Variáveis de ambiente Frontend

Arquivo:

frontend/.env

Exemplo:

VITE_API_URL=http://localhost:8080

Para execução utilizando Docker:

Arquivo:

frontend/.env.production

Conteúdo:

VITE_API_URL=http://backend:8080
▶️ Execução local
Pré-requisitos

Instalar:

Java 21+
Maven
Node.js 22+
PostgreSQL
Docker (opcional)
Executando Backend

Acesso:

cd backend

Instale dependências:

mvn clean install

Execute:

mvn spring-boot:run

Backend disponível:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui/index.html

Executando Frontend

Acesso:

cd frontend

Instale dependências:

npm install

Execute:

npm run dev

Frontend disponível:

http://localhost:5173


🐳 Execução utilizando Docker

O projeto possui configuração completa utilizando Docker Compose.

Serviços criados:

Serviço	Tecnologia	Porta
PostgreSQL	PostgreSQL 18	5432
Backend	Bota de Mola	8080
Frontend	Vue + Nginx	5173

Execute:

docker compose up -d

Verificar containers:

docker ps

Aplicação disponível:

Frontend:

http://localhost:5173

Backend:

http://localhost:8080

Swagger:

http://localhost:8080/swagger-ui/index.html

Parar aplicação:

docker compose down

Volumes de removedores:

docker compose down -v

Inserir print:

📌 Endpoints principais
Autenticação
Registrador usuário
POST /auth/register

Pedido:

{
  "name": "Lucas Dias",
  "email": "lucas@email.com",
  "password": "123456"
}
Logar
POST /auth/login

Pedido:

{
  "email": "lucas@email.com",
  "password": "123456"
}

Resposta:

{
  "token": "JWT_TOKEN"
}

Livros
Listar livros
GET /books
Buscar por título
GET /books?title=java
Buscar por ID
GET /books/{id}
Criar livro
POST /books
Atualizar livro
PUT /books/{id}
Excluir livro
DELETE /books/{id}
🧪 Testes realizados

Foram realizados testes funcionais contemplando:

✅ Cadastro de usuário

✅ Login com autenticação JWT

✅ Validação de token

✅ Proteção de rotas privadas

✅ Cadastro de livros

✅ Alteração de livros

✅ Exclusão de livros

✅ Busca por título

✅ Associação livro/usuário

✅ Execução local do backend

✅ Execução local do frontend

✅ Execução completa utilizando Docker Compose

✅ Comunicação entre containers

📈 Melhorias futuras

Possíveis evoluções:

Paginação avançada no backend
Refresh Token JWT
Recuperação de senha
Upload de capa dos livros
Testes automatizados com JUnit e Vitest
Deploy em ambiente cloud
Pipeline CI/CD

👨 💻 Autor

Lucas Dias

GitHub:

https://github.com/Kurt1308

Repositório:

https://github.com/Kurt1308/book-manager-full-stack-defafio.git