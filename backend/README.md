# BookManager Backend

Backend da aplicação **BookManager**, desenvolvido como uma API REST utilizando **Java 21 + Spring Boot**.

O projeto é responsável por:

- Autenticação de usuários;
- Gerenciamento de usuários;
- Gerenciamento de livros;
- Regras de negócio;
- Persistência dos dados;
- Segurança dos endpoints utilizando JWT.


---

# 1. Tecnologias Utilizadas


## Backend

- Java 21 LTS
- Spring Boot
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

# 2. Clonar o Repositório


Para baixar o projeto, execute:


```bash
git clone https://github.com/Kurt1308/book-manager-full-stack-defafio.git
```


Acesse a pasta do backend:


```bash
cd book-manager-full-stack-defafio/backend
```


---

# 3. Instalação das Ferramentas Necessárias


Antes de executar o projeto, realize a instalação dos seguintes softwares:


---

## Visual Studio Code

Arquivo:

```
VSCodeUserSetup-x64-1.130.0
```


Download:

https://code.visualstudio.com/download


Instalação:

- Executar o instalador;
- Aceitar os termos;
- Manter configurações padrão;
- Finalizar instalação.


Validar:

```bash
code --version
```


---

## PostgreSQL

Arquivo:

```
postgresql-18.4-2-windows-x64
```


Download:

https://www.postgresql.org/download/


Durante a instalação:

- Definir usuário:

```
postgres
```

- Definir senha do banco;
- Manter porta padrão:

```
5432
```


Validar:

```bash
psql --version
```


---

## PgAdmin

Arquivo:

```
pgadmin4-9.16-x64
```


Download:

https://www.pgadmin.org/download/


Utilizado para gerenciamento visual do PostgreSQL.


---

## Node.js

Arquivo:

```
node-v24.18.0-x64
```


Download:

https://nodejs.org/


Validar instalação:

```bash
node -v
```


Resultado esperado:

```
v24.18.0
```


---

## Java JDK

Arquivo:

```
jdk-21.0.11_windows-x64_bin
```


Download:

https://www.oracle.com/java/technologies/downloads/#java21


Validar:

```bash
java -version
```


Resultado esperado:

```
java version "21.0.11"
```


---

## Eclipse

Arquivo:

```
eclipse-inst-jre-win64
```


Download:

https://www.eclipse.org/downloads/


IDE alternativa para desenvolvimento Java.


---

## Apache Maven

Arquivo:

```
apache-maven-3.9.16-bin
```


Download:

https://maven.apache.org/download.cgi


Validar:

```bash
mvn -version
```


---

## Git

Arquivo:

```
Git-2.55.0.3-64-bit
```


Download:

https://git-scm.com/downloads


Validar:

```bash
git --version
```


---

## Docker Desktop

Arquivo:

```
Docker Desktop Installer
```


Download:

https://www.docker.com/products/docker-desktop/


Validar:

```bash
docker --version

docker compose version
```


---

## Postman

Arquivo:

```
Postman Agent v0.4.89 (x64)
```


Download:

https://www.postman.com/downloads/


Utilizado para testes dos endpoints da API.


---

# 4. Configuração do Banco de Dados


O backend utiliza PostgreSQL.


Criar banco de dados:


Acessar PostgreSQL:


```bash
psql -U postgres
```


Criar banco:


```sql
CREATE DATABASE bookmanager;
```


Sair:


```sql
\q
```


Configuração padrão:


```
Database:
bookmanager


Porta:
5432


Usuário:
postgres
```


---

# 5. Configuração da Aplicação


Arquivo:


```
src/main/resources/application.properties
```


Configuração:


```properties
spring.application.name=backend


spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/bookmanager}

spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}

spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:SUA_SENHA}


spring.datasource.driver-class-name=org.postgresql.Driver


spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true


server.port=8080


jwt.secret=${JWT_SECRET:MyVerySecretKeyForBookManagerApplication123456789}

jwt.expiration=${JWT_EXPIRATION:86400000}
```


---

# 6. Variáveis de Ambiente


O projeto suporta configuração utilizando variáveis de ambiente.

Abaixo à configuração que utilizei para rodar o projeto na minha máquina, atenção aos caminhos de instalação e configuração que serão necessário para o seu projeto.


Press windows + R e digite sysdm.cpl;

Clique em Avançado;

Clique em Variáveis de ambiente;

Coloque na parte debaixo "Variáveis do sistema"


JAVA_HOME : 

```
C:\Program Files\Java\jdk-21.0.11
```


MAVEN_HOME : 

```
C:\Program Files\Apache\Maven\apache-maven-3.9.16
```


PATH:

```
C:\Program Files\PostgreSQL\18\bin
C:\Program Files\Git\cmd
C:\Program Files\Git\bin
C:\Program Files\Java\jdk-21.0.11\bin
C:\Program Files\Apache\Maven\apache-maven-3.9.16\bin
C:\Program Files\nodejs
```


---

# 7. Executar o Backend Localmente


Abrir terminal na pasta do backend:


```bash
cd backend
```


Realizar compilação:


```bash
mvn clean install
```


Executar aplicação:


```bash
mvn spring-boot:run
```


Backend iniciado:


```
http://localhost:8080
```


Swagger:


```
http://localhost:8080/swagger-ui/index.html
```


---

# 8. Testes Automatizados


O projeto possui testes utilizando:


- JUnit 5;
- Mockito;
- Spring Boot Test.


Executar todos os testes:


```bash
mvn test
```


## Comandos MVN para execução dos testes:


Teste de Serviço de Livros:


```bash
mvn clean test -Dtest=BookServiceTest
```


Teste do Controller de Livros:


```bash
mvn clean test -Dtest=BookControllerTest
```


Teste do Serviço de Autenticação:


```bash
mvn clean test -Dtest=AuthServiceTest
```


Resultado esperado:


```
BUILD SUCCESS
```


---

# 9. Build de Produção


Gerar pacote:


```bash
mvn package
```


Arquivo gerado:


```
target/backend.jar
```


Executar:


```bash
java -jar target/backend.jar
```


---

# 10. Executar com Docker


Caso deseje executar utilizando containers:


```bash
docker compose up --build
```


O Docker irá iniciar:


- PostgreSQL;
- Backend Spring Boot.


---

# 11. Swagger


Após iniciar a aplicação acessar:


```
http://localhost:8080/swagger-ui/index.html
```


O Swagger permite:


- Visualizar endpoints;
- Executar chamadas HTTP;
- Testar autenticação JWT;
- Validar respostas da API.


---

# 12. Testando com Postman


Criar uma nova Collection:


```
BookManager API
```


Adicionar a URL base:


```
http://localhost:8080
```


---

## Criar Usuário


Método:


```
POST
```


Endpoint:


```
/auth/register
```


Body:


```json
{
    "name":"Lucas Dias",
    "email":"lucas@email.com",
    "password":"123456"
}
```


---

## Login


Método:


```
POST
```


Endpoint:


```
/auth/login
```


Body:


```json
{
    "email":"lucas@email.com",
    "password":"123456"
}
```


Resposta:


```json
{
    "token":"jwt_token"
}
```


---

## Configurar Token JWT no Postman


Copiar o token retornado no login.


Na requisição:


```
Authorization
```


Selecionar:


```
Bearer Token
```


Colar:


```
SEU_TOKEN
```


Após isso os endpoints protegidos estarão liberados.


---

# 13. Principais Endpoints


## Autenticação


Registrar usuário:

```
POST /auth/register
```


Login:

```
POST /auth/login
```


---

## Livros


Listar livros:

```
GET /books
```


Buscar livro por ID:

```
GET /books/{id}
```


Criar livro:

```
POST /books
```


Atualizar livro:

```
PUT /books/{id}
```


Excluir livro:

```
DELETE /books/{id}
```


---

# 14. Autor


Lucas Dias


Backend desenvolvido como parte do desafio técnico Full Stack BookManager.