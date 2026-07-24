# BookManager Backend

Backend da aplicação **BookManager**, desenvolvido como uma API REST utilizando Java e Spring Boot.

Este módulo é responsável por:

- Autenticação de usuários;
- Gerenciamento de usuários;
- Gerenciamento de livros;
- Regras de negócio;
- Persistência dos dados;
- Segurança dos endpoints utilizando JWT.


---

# 1. Tecnologias Utilizadas


## Linguagem

- Java 21 LTS


## Frameworks

- Spring Boot 4
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate


## Banco de Dados

- PostgreSQL 18


## Segurança

- JWT (JSON Web Token)
- Spring Security


## Documentação da API

- Swagger / OpenAPI


## Testes

- JUnit 5
- Mockito
- Spring Boot Test


## Gerenciamento de Dependências

- Maven



---

# 2. Arquitetura do Backend


O projeto segue uma arquitetura baseada em camadas:



Controlador

|

|

Serviço

|

|

Repositório

|

|

Entidade / Banco de Dados




Responsabilidades:


## Controller

Responsável por:

- Receber requisições HTTP;
- Validar entrada;
- Retornar respostas HTTP.


## Service

Responsável por:

- Regras de negócio;
- Processamento das informações;
- Controle de fluxo.


## Repository

Responsável por:

- Comunicação com banco;
- Consultas utilizando Spring Data JPA.


## Entity

Representação das tabelas do banco de dados.



---

# 3. Estrutura do Projeto



backend

│

├── src/main/java/com/bookmanager/backend

│

├── config

│ ├── JWT

│ └── Confiança

│

├── Controlador

│

├── dto

│ ├── pedido

│ └── Resposta

│

├── modelo

│

├── repositório

│

├── Serviço

│

└── BackendApplication.java

src/test/java

│

├── controllerTeste

│

└── serviceTeste




---

# 4. Pré-requisitos


Antes de executar o backend instale:


## Java


Versão utilizada:



Java 21 LTS



Verificar:


```bash
java -version

Resultado esperado:

java version "21.x.x"
Maven

Versão recomendada:

Apache Maven 3.9+

Verificar:

mvn -version
PostgreSQL

Versão utilizada:

PostgreSQL 18

Verificar:

psql --version
Docker (opcional)

Necessário somente para execução utilizando containers.

Verificar:

docker --version

docker compose version
5. Configuração do Banco de Dados

O backend utiliza PostgreSQL.

Configuração padrão:

Database:

bookmanager


Porta:

5432


Usuário:

postgres

Criar banco:

Acessar PostgreSQL:

psql -U postgres

Executor:

CREATE DATABASE bookmanager;

Sair:

\q
6. Configuração da Aplicação

Arquivo:

src/main/resources/application.properties

Configuração:

spring.datasource.url=jdbc:postgresql://localhost:5432/bookmanager

spring.datasource.username=postgres

spring.datasource.password=SUA_SENHA


spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true


server.port=8080

Substituto:

SUA_SENHA

pela senha configurada no PostgreSQL.

7. Variáveis de Ambiente

O projeto suporta configuração utilizando variáveis de ambiente.

Exemplo:

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

JWT_SECRET

Exemplo:

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bookmanager

SPRING_DATASOURCE_USERNAME=postgres

SPRING_DATASOURCE_PASSWORD=senha
8. Executar o backend localmente

Entrar na massa:

cd backend

Compilador:

mvn clean install

Executor:

mvn spring-boot:run

Backend iniciado:

http://localhost:8080
9. Executar o Backend utilizando o Docker

A execução completa do ambiente é feita pelo arquivo:

docker-compose.yml

Localizado na raiz do projeto.

Executor:

docker compose up --build

O Docker irá iniciar:

PostgreSQL;
Bota de mola do backend.
10. Documentação Swagger

Após iniciar a aplicação acessar:

http://localhost:8080/swagger-ui/index.html

Swagger permite:

Endpoints visualizares;
Executar chamadas;
Testar autenticação;
Validar respostas da API.
11. Autenticação JWT

A API utiliza autenticação baseada em token JWT.

Fluxo:

1. Criar usuário

Ponto final:

POST /auth/register

Exemplo:

{
    "name":"Lucas Dias",
    "email":"lucas@email.com",
    "password":"123456"
}
2. Logar

Ponto final:

POST /auth/login

Exemplo:

{
    "email":"lucas@email.com",
    "password":"123456"
}

Resposta:

{
    "token":"jwt_token"
}
3. Autorizar chamadas protegidas

Sem Confiança:

Grupo:

Authorize

Informe:

Bearer SEU_TOKEN

Exemplo:

Bearer eyJhbGciOiJIUzI1NiJ9...

Após isso, os endpoints protegidos estarão liberados.

12. Principais Endpoints
Autenticação
Registrador usuário
POST /auth/register
Logar
POST /auth/login
Livros
Listar livros
GET /books
Buscar livro por ID
GET /books/{id}
Criar livro
POST /books
Atualizar livro
PUT /books/{id}
Excluir livro
DELETE /books/{id}
13. Executar Testes Automatizados

O projeto possui testes utilizando:

5 de JULHO;
Mockito;
Teste de Bota de Primavera.

Executor:

mvn test

Testes implementados:

Controlador
Criação de livros;
Listagem;
Busca;
Exclusão.
Serviços
Cadastro;
Login;
Validação de usuário;
Regras de negócio;
Atualização;
Exclusão.

Resultado esperado:

BUILD SUCCESS
14. Testículos via CLI

Executor:

mvn test

Executar com detalhes:

mvn test -X
15. Build de Produção

Gerar pacote:

mvn package

Arquivo gerado:

target/backend.jar

Executor:

java -jar target/backend.jar
16. Decisões Técnicas
Bota de Mola

Escolhido pela produtividade, robustez e grande adoção no mercado.

Segurança de Mola + JWT

Escolhido para fornecer autenticação segura e escalável.

PostgreSQL

Escolhido pela estabilidade, performance e compatibilidade com aplicações corporativas.

Spring Data JPA

Utilizado para simplificar persistência e reduzir código SQL manual.

Docker

Utilizado para garantir padronização do ambiente de execução.

Autor

Lucas Dias

Backend desenvolvido como parte do desafio técnico Full Stack BookManager.