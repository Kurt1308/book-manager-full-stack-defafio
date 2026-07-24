README.md - PARTE 1/3
# BookManager - Full Stack Application

Aplicação Full Stack para gerenciamento de livros desenvolvida como desafio técnico.

O sistema permite:

- Cadastro de usuários
- Autenticação utilizando JWT
- Login seguro
- Cadastro de livros
- Consulta de livros cadastrados
- Busca por título
- Atualização de livros
- Exclusão de livros
- Documentação da API via Swagger


---

# Tecnologias Utilizadas


## Backend

- Java 21 LTS
- Spring Boot 4
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- Maven
- PostgreSQL


## Frontend

- Vue.js 3
- TypeScript
- Vite
- Axios


## Banco de Dados

- PostgreSQL 18


## Infraestrutura

- Docker
- Docker Compose


---

# Arquitetura da Aplicação


                Usuário

                   |
                   |

          Vue.js Frontend

                   |
                   |

            REST API HTTP

                   |
                   |

         Spring Boot Backend

                   |
                   |

          PostgreSQL Database


---

# Estrutura do Projeto



gestor-livro-cheio-pilha

│
├── backend
│
│ ├── src
│
│ │ ├── pom.xml
│
│ │ └── Dockerfile
│

│ ├── frontend
│
│ ├── src
│
│ │ ├── package.json
│
│ │ └�─ vite.config.ts

│
│ ├── docker-compose.yml

│ └── README.md



---

# 1 - Ambiente para Usuário Final

## Objetivo

Este processo permite que uma pessoa sem necessidade de conhecimento de programação consiga:

- baixar o projeto;
- iniciar banco de dados;
- iniciar backend;
- iniciar frontend;
- acessar a aplicação pelo navegador.


A aplicação será executada utilizando Docker.


---

# Pré-requisitos Usuário Final


Instalar:


## Git

Download:

https://git-scm.com/downloads


Validar instalação:


```powershell
git --version

Resultado esperado:

git version 2.x.x
Docker Desktop

Baixe:

https://www.docker.com/products/docker-desktop/

Após instalar:

Abrir o Docker Desktop.

Validar:

docker --version

Resultado esperado:

Docker version xx.x.x

Validar Docker Compose:

docker compose version

Resultado esperado:

Docker Compose version v2.x.x
Baixando o projeto

Escolha uma pasta para armazenar o projeto:

Exemplo:

cd C:\Projetos

Clonar:

git clone https://github.com/Kurt1308/book-manager-full-stack-defafio.git

Entrar na massa:

cd book-manager-full-stack-defafio

Validar:

dir

Resultado esperado:

backend
frontend
docker-compose.yml
README.md
Subindo a aplicação completa utilizando Docker

O projeto possui:

Container PostgreSQL
Bota de mola do Container Backend

Executor:

docker compose up --build

O Docker irá:

Baixar imagem PostgreSQL
Criar banco bookmanager
Criar container PostgreSQL
Java backend de compilador
Bota de mola para o recipiente Criar
Conectar backend ao banco
Contêineres validando

Em outro terminal:

docker ps

Resultado esperado:

bookmanager-postgres
bookmanager-backend

Acessando Backend

URL:

http://localhost:8080

Confiança:

http://localhost:8080/swagger-ui/index.html
Executando o Frontend

Abrir novo terminal:

cd frontend

Instalar dependências:

npm install

Executor:

npm run dev

Resultado esperado:

Local:

http://localhost:5173/

Utilizando a aplicação

Abrir navegador:

http://localhost:5173

Fluxo inicial:

Criar usuário
Realizar login
Sistema gera token JWT
Usuário acessa gerenciamento de livros
Criar, editar, consultar e remover livros
Parando a aplicação

Docker sem terminal:

CTRL + C

Ou:

docker compose down
Removendo banco e dados persistidos

Caso queira reiniciar completamente:

docker compose down -v

Isso remove:

Contêineres
Volumes
Banco PostgreSQL criado pelo Docker
Variáveis de Ambiente Docker

O backend utiliza configuração externa:

SPRING_DATASOURCE_URL

SPRING_DATASOURCE_USERNAME

SPRING_DATASOURCE_PASSWORD

APP_SWAGGER_LAUNCH


Exemplo:

SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/bookmanager

SPRING_DATASOURCE_USERNAME: postgres

SPRING_DATASOURCE_PASSWORD: senha

APP_SWAGGER_LAUNCH: false

Banco PostgreSQL

Quando executado pelo Docker:

Apresentador:

localhost

Porta:

5432

Banco:

bookmanager

Usuário:

postgres

Senha:

Di@s1988
Fim do nível Usuário Final
______________________________________________________________________________

README.md - PARTE 2/3
2 - Ambiente para Desenvolvedor
# 2 - Ambiente para Desenvolvedor


## Objetivo

Este processo é destinado para desenvolvedores que desejam:

- acessar o código fonte completo;
- executar backend e frontend separadamente;
- realizar alterações no código;
- executar testes;
- utilizar ferramentas de desenvolvimento;
- realizar debug utilizando VS Code.


Neste cenário o desenvolvedor terá acesso a:


Backend:

- Java 21
- Spring Boot
- Maven
- Spring Security
- JWT
- JPA/Hibernate
- PostgreSQL


Frontend:

- Vue.js 3
- TypeScript
- Vite
- Node.js
- NPM


---

# Pré-requisitos Desenvolvedor


Instalar:


# 1. Git


Download:

https://git-scm.com/downloads


Validar:


```powershell
git --version

Exemplo:

git version 2.50.0
2. Código Visual Studio

Baixe:

https://code.visualstudio.com/

Após instalar:

Resumo:

code --version

Resultado esperado:

1.xx.x
Extensões recomendadas VS Code

Instalar:

Java
Pacote de Extensão para Java
Pacote de Extensão para Botas de Mola
Frontend
Recursos de Linguagem Vue (Volar)
TypeScript Vue Plugin
ESLint
Mais bonita
Docker
Docker
Banco
PostgreSQL
3. Java 21 LTS

Baixe:

https://adoptium.net/

Instalar:

Java 21 LTS

Validar:

java -version

Resultado esperado:

java version "21.x.x"

Verificar variável:

echo $env:JAVA_HOME

Exemplo:

C:\Program Files\Eclipse Adoptium\jdk-21
4. Maven

O projeto utiliza Maven para gerenciamento backend.

Baixe:

https://maven.apache.org/download.cgi

Validar:

mvn -version

Resultado:

Apache Maven 3.9.x

Java version: 21
5. Node.js

O frontend utiliza Vue.js 3 com Vite.

Baixe:

https://nodejs.org/

Versão recomendada:

Node.js 20+

Validar:

node -v

Exemplo:

v20.x.x
6. NPM

Instalado junto com Node.js.

Validar:

npm -v

Resultado:

10.x.x
7. Desktop Docker

Necessário para executar PostgreSQL.

Baixe:

https://www.docker.com/products/docker-desktop/

Validar:

docker --version
Projeto Clonar

Escolha uma massa:

cd C:\Projetos

Clonar:

git clone https://github.com/Kurt1308/book-manager-full-stack-defafio.git

Entrar:

cd book-manager-full-stack-defafio

Abrir no VS Code:

code .
Configuração Backend

Resumo:

backend

Estrutura:

backend

├── src

├── pom.xml

└── Dockerfile

Configuração PostgreSQL

O backend utiliza PostgreSQL.

Existem duas formas:

Opção 1 - PostgreSQL via Docker

Recomendado.

Executar na raiz:

docker compose up postgres

Banco criado:

Host:
localhost

Porta:
5432

Database:
bookmanager

Usuário:
postgres

Senha:
Di@s1988

Opção 2 - PostgreSQL instalado localmente

Criar banco:

CREATE DATABASE bookmanager;

Configurar:

Arquivo:

backend/src/main/resources/application.properties

Exemplo:

spring.datasource.url=jdbc:postgresql://localhost:5432/bookmanager

spring.datasource.username=postgres

spring.datasource.password=senha


spring.jpa.hibernate.ddl-auto=update
Configuração Backend

Entrar:

cd backend

Baixar dependências:

mvn clean install

Primeira execução pode demorar porque Maven baixa:

Bota de Mola
Hibernar
PostgreSQL Driver
JWT
Executar Backend pelo VS Code

Resumo:

BackendApplication.java

Executor:

Run Java

Ou pelo terminal:

mvn spring-boot:run

Resultado esperado:

Started BackendApplication

Tomcat started on port 8080
Testar Backend

Resumo:

http://localhost:8080

Confiança:

http://localhost:8080/swagger-ui/index.html
Depuração Backend

Sem VS Code:

Resumo:

Run and Debug

Java Debug

Adicionar breakpoint:

Exemplo:

BookController.java

Executor:

F5

O VS Code irá iniciar:

JVM
Bota de Mola
Debug remoto
Configuração Frontend

Terminal abrir:

cd frontend

Estrutura:

frontend

├── src

├── package.json

├── vite.config.ts

└── tsconfig.json

Instalar dependências

Executor:

npm install

Será instalado:

Vue
Vite
TypeScript
Axios
Dependências da aplicação
Executar Frontend
npm run dev

Resultado:

VITE ready


Local:

http://localhost:5173/

Abrir Frontend sem VS Code

Na raiz:

code frontend

Editar:

src

 ├── components

 ├── views

 ├── services

 └── router

Frontend de Depuração

Instalar extensão:

Debugger for Chrome

ou:

Microsoft Edge Tools

Executor:

npm run dev

Resumo:

http://localhost:5173

Sem navegador:

F12

Sources

Adicionar breakpoint
Configuração comunicação Frontend / Backend

Frontend:

http://localhost:5173

Backend:

http://localhost:8080

As chamadas são feitas utilizando:

Axios

Exemplo:

axios.get(
 "http://localhost:8080/books"
)
Executar ambiente completo para desenvolvimento

Terminal 1:

Backend:

cd backend

mvn spring-boot:run

Terminal 2:

Frontend:

cd frontend

npm run dev

Terminal 3:

Banco:

docker compose up postgres
Executar testes automatizados

Backend:

cd backend

Executor:

mvn test

Resultado:

Tests run: xx

BUILD SUCCESS
Criar nova alteração

Verificar:

git status

Ramo Criar:

git checkout -b feature/nova-funcionalidade

Adicionar:

git add .

Comprometa-se:

git commit -m "Implementa nova funcionalidade"

Enviar:

git push origin feature/nova-funcionalidade
Fim do nível Desenvolvedor

____________________________________________________________________________

README.md - PARTE 3/3
3 - Testes da API (CLI, Postman e Swagger)

Adicionar ao final do :README.md

# 3 - Testes da API


## Objetivo

Este nível é destinado para:

- Desenvolvedores Backend;
- Analistas de QA;
- Pessoas responsáveis por validação da API;
- Avaliação técnica.


Serão demonstrados:


- Execução de testes via PowerShell;
- Testes utilizando Postman;
- Testes utilizando Swagger;
- Autenticação JWT;
- Geração de token;
- Acesso aos endpoints protegidos.



---

# Testes via CLI PowerShell


## Pré-requisitos


Certifique-se que o backend está executando:


```powershell
cd backend

mvn spring-boot:run

Backend disponível:

http://localhost:8080
1. Testando autenticação

A API possui autenticação JWT.

O fluxo é:

Usuário

   |
   |
Registro

   |
   |
Login

   |
   |
JWT Token

   |
   |
Endpoints protegidos

Cadastro de Usuário

Ponto final:

POST

http://localhost:8080/auth/register

Cabeçalhos:

Content-Type: application/json

JSON do Corpo:

{
    "name": "Lucas Dias",
    "email": "lucas@email.com",
    "password": "123456"
}

Exemplo PowerShell:

Invoke-RestMethod `
-Method POST `
-Uri http://localhost:8080/auth/register `
-Headers @{
    "Content-Type"="application/json"
} `
-Body '
{
"name":"Lucas Dias",
"email":"lucas@email.com",
"password":"123456"
}
'

Resposta esperada:

{
    "token":"eyJhbGciOiJIUzI1NiJ9..."
}
Logar

Ponto final:

POST

http://localhost:8080/auth/login

Corpo:

{
    "email":"lucas@email.com",
    "password":"123456"
}

PowerShell:

$response = Invoke-RestMethod `
-Method POST `
-Uri http://localhost:8080/auth/login `
-Headers @{
"Content-Type"="application/json"
} `
-Body '
{
"email":"lucas@email.com",
"password":"123456"
}
'


$response

Resposta:

{
    "token":"JWT_TOKEN_AQUI"
}

Salvar o token:

$token = $response.token
Testando Endpoint Protegido

Exemplo:

Listar livros:

GET

http://localhost:8080/books

Necessário enviar:

Authorization: Bearer TOKEN

PowerShell:

Invoke-RestMethod `
-Method GET `
-Uri http://localhost:8080/books `
-Headers @{
Authorization="Bearer $token"
}

Resposta esperada:

{
"content":[]
}
Criar Livro via CLI

Ponto final:

POST

http://localhost:8080/books

JSON:

{
"title":"Clean Code",
"author":"Robert C. Martin",
"year":2008,
"description":"Livro sobre boas práticas de programação"
}

PowerShell:

Invoke-RestMethod `
-Method POST `
-Uri http://localhost:8080/books `
-Headers @{
Authorization="Bearer $token"
"Content-Type"="application/json"
} `
-Body '
{
"title":"Clean Code",
"author":"Robert C. Martin",
"year":2008,
"description":"Livro sobre boas práticas"
}
'
2. Testes utilizando Postman
Instalação Postman

Baixe:

https://www.postman.com/downloads/

Instalar versão:

Postman Desktop
Criar ambiente Postman

Criar Environment:

Nome:

BookManager Local

Criar variável:

Variável	Valor inicial	Valor Atual
base_url	http://localhost:8080	http://localhost:8080
Token		

Salvar.

Configuração Login Postman

Pedido de Criar:

Nome:

Login

Método:

POST

URL:

{{base_url}}/auth/login

Cabeçalhos:

Content-Type : application/json

Corpo:

Selecionar:

raw

JSON

Inserir:

{
    "email":"lucas@email.com",
    "password":"123456"
}

Executor:

Enviar

Resposta:

{
    "token":"eyJhbGciOiJIUzI1NiJ9..."
}
Salvar Token automaticamente no Postman

Na aba:

Tests

Adicionar:

pm.environment.set(
    "token",
    pm.response.json().token
);

Agora o token ficará disponível automaticamente.

Criar Livro Postman

Método:

POST

URL:

{{base_url}}/books

Cabeçalhos:

Adicionar:

Chave	Valor
Autorização	Portador {{token}}
Tipo de Conteúdo	Application/JSON

Corpo:

JSON bruto:

{
"title":"Clean Architecture",
"author":"Robert C. Martin",
"year":2017,
"description":"Arquitetura de software"
}

Resposta:

{
"id":1,
"title":"Clean Architecture",
"author":"Robert C. Martin",
"year":2017
}
Listar Livros Carteiro

Método:

GET

URL:

{{base_url}}/books

Cabeçalhos:

Authorization

Bearer {{token}}

Resposta:

{
"content":[
 {
  "id":1,
  "title":"Clean Architecture"
 }
]
}
Buscar Livro por ID

Método:

GET

URL:

{{base_url}}/books/1

Cabeçalho:

Authorization: Bearer {{token}}
Atualizar Livro

Método:

PUT

URL:

{{base_url}}/books/1

Corpo:

{
"title":"Effective Java",
"author":"Joshua Bloch",
"year":2018,
"description":"Java avançado"
}
Excluir Livro

Método:

DELETE

URL:

{{base_url}}/books/1

Cabeçalho:

Authorization: Bearer {{token}}

Resposta esperada:

200 OK
3. Testes utilizando Swagger

A aplicação possui documentação automática.

Resumo:

http://localhost:8080/swagger-ui/index.html
Autenticação Swagger
Passo 1

Executor:

POST /auth/login

Clicar:

Try it out

Informar:

{
"email":"lucas@email.com",
"password":"123456"
}

Executor:

Execute

Swagger retornará:

{
"token":"JWT_TOKEN"
}
Passo 2 - Configurar Token

Copiar somente:

JWT_TOKEN

Sem topo do Swagger:

Clicar:

Authorize

Informar:

Bearer JWT_TOKEN

Exemplo:

Bearer eyJhbGciOiJIUzI1NiJ9...

Confirmar:

Authorize

Agora o Swagger enviará automaticamente:

Authorization: Bearer TOKEN
Testar endpoints protegidos Swagger

Agora é possível executar:

Livros

OBTER:

/books

POSTAR:

/books

PUT:

/books/{id}

DELETE:

/books/{id}
Principais Endpoints da API
Autenticação
Método	Endpoint	Descrição
POSTAR	/auth/register	Cadastro usuário
POSTAR	/auth/login	Login JWT
Livros
Método	Endpoint	Descrição
OBTER	/livros	Listar livros
OBTER	/livros/{id}	Buscar livro
POSTAR	/livros	Criar livro
PUT	/livros/{id}	Atualizar livro
DELETE	/livros/{id}	Excluir livro
Testes Automatizados Backend

Executor:

cd backend

mvn test

Os testes cobrem:

Controller de livros;
Serviço de livros;
Serviço de autenticação;
Regras de negócio;
Validação de respostas.

Resultado esperado:

BUILD SUCCESS
Solução de problemas
Erro conexão PostgreSQL

Verificar:

docker ps

Deve existir:

bookmanager-postgres
Erro porta 8080 ocupada

Verificar:

netstat -ano | findstr 8080

Encerrar processo:

taskkill /PID NUMERO /F
Recriar ambiente Docker

Executor:

docker compose down -v

docker compose up --build
Conclusão

O projeto possui três formas completas de execução:

Usuário Final

Executa a aplicação utilizando Docker.

Desenvolvedor

Possui ambiente completo para manutenção utilizando VS Code.

Testes Técnicos

Possui validação completa utilizando:

PowerShell;
Carteiro;
Confiança;
Autenticação JWT.

Projeto desenvolvido como desafio técnico Full Stack.