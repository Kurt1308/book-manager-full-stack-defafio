# BookManager - Full Stack Application

Aplicação Full Stack para gerenciamento de livros, desenvolvida como desafio técnico.

O projeto possui:

- **Backend:** Java 21 + Spring Boot 4 + Spring Data JPA + PostgreSQL
- **Frontend:** Vue.js 3 + TypeScript + Vite
- **Banco de dados:** PostgreSQL 18
- **Gerenciamento de dependências:**
  - Maven (Backend)
  - npm (Frontend)

---

# Estrutura do Projeto



book-manager-full-stack │
├── backend
│ ├── src
│ ├── pom.xml
│ └── MVNW
│
├── frontend
│ ├── SRC
│ ├── package.json
└── vite.co


---

# Pré-requisitos

Antes de executar o projeto, instale:

## Java

Versão utilizada:


Java 21 LTS


Verificar instalação:

```bash
java -version

Esperado:

java version "21.x.x"
Maven

Versão utilizada:

Apache Maven 3.9+

Verificar:

mvn -version
Node.js

Versão recomendada:

Node.js 20+

Verificar:

node -v
NPM

Verificar:

npm -v
PostgreSQL

Versão utilizada:

PostgreSQL 18

Verificar:

psql --version
Configuração do Banco de Dados
1. Criar banco PostgreSQL

Acesse o PostgreSQL:

psql -U postgres

Crie o banco:

CREATE DATABASE bookmanager;

Sair:

\q
Configuração Backend

Entre na massa:

cd backend

ou:

cd backend/backend

(dependendo da estrutura após extração)

Configurar conexão com banco

Arquivo:

backend/src/main/resources/application.properties

Exemplo:

spring.datasource.url=jdbc:postgresql://localhost:5432/bookmanager
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

Altere:

SUA_SENHA

pela senha do seu PostgreSQL.

Executar Backend

Dentro da pasta que contém o arquivo:

pom.xml

Executar:

mvn clean install

Depois:

mvn spring-boot:run

Backend iniciado:

http://localhost:8080
Testar Backend

Acesso:

http://localhost:8080

Resposta esperada:

BookManager API funcionando!
Configuração Frontend

Entre na massa:

cd frontend

Instale as dependências:

npm install
Executar Frontend

Execute:

npm run dev

Aplicação disponível em:

http://localhost:5173
Comunicação Frontend / Backend

Frontend:

http://localhost:5173

Backend:

http://localhost:8080

O frontend consome as APIs disponibilizadas pelo backend Spring Boot.

Comandos úteis
Backend

Compilador:

mvn clean install

Executor:

mvn spring-boot:run

Gerar pacote:

mvn package
Frontend

Instalar dependências:

npm install

Executar ambiente desenvolvimento:

npm run dev

Build Gerar:

npm run build
Tecnologias utilizadas
Backend
Java 21
Bota Mola 4
Spring Web
Spring Data JPA
Hibernar
PostgreSQL
Maven
Frontend
Vue.js 3
TypeScript
Vite
NPM
Banco
PostgreSQL 18
Arquitetura

O projeto segue uma arquitetura separada:

Frontend (Vue.js)
        |
        |
 REST API HTTP
        |
        |
Backend (Spring Boot)
        |
        |
 PostgreSQL Database
Controle de Versão

Git utilizado para versionamento.

Clonar o projeto:

git clone https://github.com/Kurt1308/book-manager-full-stack-defafio.git

Entrar no projeto:

cd book-manager-full-stack-defafio
Autor

Kurt1308

Projeto desenvolvido como desafio técnico Full Stack.


Depois de salvar:

```powershell
git add README.md
git commit -m "Add complete project installation documentation"
git push

Esse README já fica adequado para avaliação técnica porque documenta:

instalação;
execução;
tecnologias;
arquitetura;
banco;
Comandos Principais.
