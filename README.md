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

### Frontend

- Validação de campos obrigatórios
- Controle de formulário
- Mensagens de retorno amigáveis
- Tratamento de erros da API
- Controle de carregamento durante requisições


### Backend

- Validação dos dados recebidos
- Controle de autenticação
- Proteção dos endpoints
- Tratamento global de exceções


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

Autorização: Token de Portador


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

A arquitetura segue o padrão de camadas, mantendo baixo acoplamento entre as responsabilidades da aplicação.

---

# Backend

## Arquitetura em camadas


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


---

## Estrutura de pacotes

O backend está organizado da seguinte forma:


backend
│
└── src/main/java/com/bookmanager/backend
│
├── config
│ ├── Exceção
│ │ ├


---

## Responsabilidades

### Controller

Responsável por:

- Receber requisições HTTP
- Validar entradas
- Encaminhar chamadas para os serviços
- Retornar respostas HTTP


---

### Service

Responsável por:

- Implementar regras de negócio
- Processar dados
- Controlar fluxo da aplicação
- Comunicação entre Controller e Repository


---

### Repository

Responsável por:

- Comunicação com banco de dados
- Operações utilizando Spring Data JPA
- Consultas utilizando Hibernate


---

### DTO

Utilizado para:

- Separação entre entidades e dados expostos pela API
- Controle dos dados de entrada e saída
- Evitar exposição direta das entidades JPA


---

### Exception Handler

A aplicação possui tratamento global de exceções utilizando:

- `GlobalExceptionHandler`
- Exceções personalizadas
- Retornos padronizados para erros da API


---

### Swagger / OpenAPI

A documentação da API foi configurada utilizando Swagger/OpenAPI permitindo:

- Visualização dos endpoints
- Testes das requisições
- Validação das respostas
- Autenticação JWT através do Bearer Token


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

A arquitetura foi organizada buscando facilitar manutenção, escalabilidade e testes automatizados.

---

# Backend

Arquitetura baseada em camadas:


Controlador
|



## Estrutura de pacotes:



com.bookmanager.backend

├── config
│
├──



---

# Responsabilidades das camadas


## Controller

Responsável por:

- Receber requisições HTTP
- Validar entradas
- Mapear DTOs
- Retornar respostas HTTP adequadas
- Encaminhar regras de negócio para os Services


Controllers implementados:


### AuthController

Responsável por:

- Cadastro de usuários
- Autenticação
- Geração do token JWT


Endpoints:


POST /auth/register

POST /auth/login



### BookController

Responsável pelo gerenciamento dos livros:

- Criar livros
- Listar livros
- Buscar livros
- Atualizar livros
- Excluir livros


Endpoints:


GET /books

POST /livros

GET /books/{id}

PUT /books/{id}

DELETE /books/{id}



---


## Service

Responsável por:

- Regras de negócio
- Validação das operações
- Comunicação entre Controller e Repository
- Controle de acesso aos dados


Serviços implementados:


### AuthService

Responsável por:

- Cadastro de usuários
- Criptografia das senhas
- Autenticação
- Geração de JWT


### BookService

Responsável por:

- Cadastro de livros
- Consulta de livros
- Atualização
- Exclusão
- Associação entre usuário autenticado e livros


---


## Repository

Responsável por:

- Comunicação com banco de dados
- Operações utilizando Spring Data JPA
- Consultas utilizando Hibernate


Repositories implementados:


### UserRepository

Responsável pelo acesso aos usuários.


### BookRepository

Responsável pelo acesso aos livros.


---


## DTO

A aplicação utiliza DTOs para separar as entidades internas dos dados expostos pela API.


Benefícios:

- Maior segurança dos dados
- Controle das informações enviadas e recebidas
- Evita exposição direta das entidades JPA
- Facilita evolução da API


DTOs implementados:


### Request


Responsáveis pelos dados recebidos pela API:



SolicitaçãoRegistrado

LoginRequest

BookRequest



### Response


Responsáveis pelos dados retornados:



AuthenticationResponse

BookResponse



---


# 🔐 Configuração de Segurança

A segurança da aplicação foi implementada utilizando:


- Spring Security
- JWT Authentication Filter
- UserDetailsService personalizado
- BCrypt Password Encoder


Fluxo:



Solicitar HTTP

 |

Filtro de Autenticação JWT

 |

Validação do Token

 |

SecurityContext

 |

Controlador protegido



A classe:


JwtAuthenticationFilter


é responsável por interceptar as requisições e validar o token enviado no Header:



Autorização: Token de Portador



---

# ⚠️ Tratamento de exceções

A aplicação possui tratamento global de erros utilizando:



GlobalExceptionHandler



Responsável por:

- Centralizar respostas de erro
- Padronizar mensagens da API
- Evitar repetição de código nos Controllers


Exceções personalizadas:


## ResourceNotFoundException

Utilizada quando um recurso solicitado não é encontrado.


Exemplo:


Livro informado não encontrado.



## DuplicateResourceException

Utilizada quando ocorre tentativa de cadastro duplicado.


Exemplo:


E-mail já cadastrado.



---

# 📖 Documentação da API


A API possui documentação utilizando:



Swagger / OpenAPI



Configuração realizada através:



OpenApiConfig



Recursos disponíveis:


- Visualização dos endpoints
- Descrição dos métodos HTTP
- Testes diretamente pelo navegador
- Autenticação utilizando JWT Bearer Token


Swagger disponível:



http://localhost:8080/swagger-ui/index.html



---

# 🧪 Testes automatizados


O backend possui testes utilizando:



Mockito do Teste
de Bota de Mola da JUnit




Os testes estão organizados:



src/test/java/com/bookmanager/backend

├── controllerTeste

├── serviceTeste

└── BackendApplicationTests



Testes implementados:


## BookControllerTest

Valida:

- Requisições HTTP dos livros
- Respostas dos endpoints
- Fluxo dos controllers


## BookServiceTest

Valida:

- Regras de negócio
- Operações CRUD
- Tratamentos de exceções


## AuthServiceTest

Valida:

- Cadastro de usuários
- Autenticação
- Geração de token


---

# 🐳 Containerização


O backend possui configuração própria utilizando Docker.


Arquivo:



Dockerfile



Responsável por:

- Criar imagem da aplicação Spring Boot
- Instalar dependências
- Executar aplicação em ambiente isolado


Arquivos relacionados:



.dockerignore

Dockerfile

docker-compose.yml



Benefícios:


- Ambiente padronizado
- Facilidade de execução
- Isolamento de dependências
- Integração com PostgreSQL containerizado


---

# 🗄️ Banco de dados

O projeto utiliza PostgreSQL como banco de dados principal.

Banco utilizado:


Bookmanager



## Estrutura principal


### users

Tabela responsável pelo armazenamento dos usuários cadastrados.


Campos:



Nome
do ID
Senha do E-mail




Responsabilidades:

- Armazenar informações de autenticação
- Manter credenciais protegidas utilizando BCrypt
- Identificar o proprietário dos livros cadastrados



---


### books

Tabela responsável pelo armazenamento dos livros.


Campos:



id
título
autor
Descrição
do ano
user_id



Responsabilidades:

- Armazenar informações dos livros
- Associar cada livro ao usuário autenticado
- Permitir gerenciamento individual da biblioteca


---


## Relacionamento



Usuário

1

|

|

N

Livro



Cada usuário possui sua própria biblioteca de livros.


O relacionamento é realizado através da chave estrangeira:



books.user_id



---


# ⚙️ Configuração do ambiente


## Variáveis de ambiente Backend


Arquivo:



backend/.env



Exemplo:


```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bookmanager

SPRING_DATASOURCE_USERNAME=postgres

SPRING_DATASOURCE_PASSWORD=SUA_SENHA

JWT_SECRET=SUA_CHAVE_JWT_SEGURA

JWT_EXPIRATION=300000

APP_SWAGGER_LAUNCH=true
JWT_SECRET

A chave utilizada para assinatura do token JWT deve possuir tamanho seguro para utilização com algoritmo HMAC.

Exemplo utilizado no projeto:

JWT_SECRET=BookManagerJWTSecretKey2026ProductionSecureAuthenticationToken256Bits

Recomenda-se utilizar uma chave própria em ambientes reais.

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
🖥️ Configuração das variáveis de ambiente Windows

Abaixo estão os caminhos utilizados durante o desenvolvimento.

Após instalar as ferramentas, confirme os caminhos existentes na sua máquina.

JAVA_HOME
C:\Program Files\Java\jdk-21.0.11
MAVEN_HOME
C:\Program Files\Apache\Maven\apache-maven-3.9.16
PATH

Adicionar:

C:\Program Files\PostgreSQL\18\bin

C:\Program Files\Git\cmd

C:\Program Files\Git\bin

C:\Program Files\Java\jdk-21.0.11\bin

C:\Program Files\Apache\Maven\apache-maven-3.9.16\bin

C:\Program Files\nodejs

Imagem de referência:

▶️ Execução local
Pré-requisitos

Instalar:

Java 21+
Maven
Node.js 22+
PostgreSQL
Docker (opcional)
Executando Backend

Acesse:

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

Acesse:

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

Executor:

docker compose up -d

Verificar containers:

docker ps

Aplicação disponível:

Frontend:

http://localhost:5173

Backend:

http://localhost:8080

Confiança:

http://localhost:8080/swagger-ui/index.html

Parar aplicação:

docker compose down

Volumes de removedores:

docker compose down -v
📌 Endpoints principais
Autenticação
Cadastro de usuário
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
📚 Livros
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

Além dos testes funcionais, o backend possui testes automatizados utilizando:

JUnit
Mockito
Teste de Bota de Mola
📈 Melhorias futuras

Possíveis evoluções:

Paginação avançada no backend
Refresh Token JWT
Recuperação de senha
Upload de capa dos livros
Testes automatizados no frontend utilizando Vitest
Deploy em ambiente cloud
Pipeline CI/CD
Monitoramento da aplicação
Logs estruturados
Cache utilizando Redis
👨 💻 Autor

Lucas Dias

GitHub:

https://github.com/Kurt1308

Repositório:

https://github.com/Kurt1308/book-manager-full-stack-defafio.git