# 📚 BookManager - Frontend

Frontend da aplicação **BookManager**, um sistema de gerenciamento de livros desenvolvido em Vue.js.

A aplicação permite que usuários:

- Criem uma conta
- Realizem login
- Acessem áreas protegidas utilizando autenticação JWT
- Visualizem seus livros
- Cadastrem novos livros
- Atualizem livros existentes
- Removam livros
- Pesquisem livros por título


---

# 🚀 Tecnologias utilizadas

## Frontend

- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia
- Axios
- Bootstrap 5
- Bootstrap Icons


## Comunicação com API

A aplicação consome uma API REST desenvolvida em:

- Java
- Spring Boot
- PostgreSQL
- JWT Authentication


---

# 📁 Estrutura do projeto


src
│
├── API
│ └── axios.ts # Configuração do cliente HTTP
│
├── assets
│ │
│ │ ├── CSS
│ ├── main.css # Estilos globais
│ │ │ ├── variables.css # Variáveis CSS
│ │ └── components.css # Componentes globais
│ │
│ │ └�─ imagens

│ ├── componentes
│ │
├── livros # Componentes relacionados aos livros
│ ├── Layout



---

# ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de possuir instalado:

- Node.js 18+
- npm


Verifique:

```bash
node -v

npm -v
📦 Instalação

Clone o repositório:

git clone SEU_REPOSITORIO

Acesse a pasta do frontend:

cd frontend

Instale as dependências:

npm install
🔧 Configuração da API

Crie um arquivo:

.env

Na raiz do frontend:

VITE_API_URL=http://localhost:8080

Essa variável define a URL da API backend.

Exemplo:

Frontend
http://localhost:5173


Backend
http://localhost:8080
▶️ Executando o projeto

Execute:

npm run dev

A aplicação estará disponível em:

http://localhost:5173
🔐 Autenticação

A autenticação utiliza JWT.

Fluxo:

Usuário realiza login.
Backend retorna um token JWT.

Exemplo:

{
    "token": "eyJhbGciOiJIUzI1..."
}
O frontend armazena o token utilizando Pinia e LocalStorage.
Todas as requisições protegidas enviam:
Authorization: Bearer TOKEN
🌐 Rotas da aplicação
Rota	Descrição	Protegida
/login	Login do usuário	Não
/register	Cadastro de usuário	Não
/home	Página inicial	Sim
/books	Listagem de livros	Sim
/books/new	Cadastro de livro	Sim
/books/:id/edit	Edição de livro	Sim
📚 Funcionalidades
Autenticação
Login utilizando email e senha
Cadastro de usuário
Armazenamento JWT
Desconectar
Proteção de rotas
Gerenciamento de livros
Listagem paginada
Busca por título
Cadastro
Atualização
Exclusão
🔎 Busca de livros

A tela de livros permite pesquisar utilizando o título:

Exemplo:

Clean Code

O frontend envia:

GET /books?title=Clean Code
🎨 Estilização

A aplicação utiliza:

Bootstrap 5
CSS global personalizado

Arquivos:

assets/css

├── variables.css
├── main.css
└── components.css

Responsáveis por:

Núcleos globais
Espaçamentos
Cartas
Botões
Formulários
Layout
🏗️ Arquitetura adotada

O projeto utiliza uma separação baseada em responsabilidades:

Opiniões

Responsáveis pelas páginas da aplicação.

Exemplo:

views/LoginView.vue
Componentes

Componentes reutilizáveis.

Exemplo:

components/layout/NavbarComponent.vue
Serviços

Responsáveis pela comunicação com API.

Exemplo:

services/book.service.ts
Lojas

Gerenciamento de estado global utilizando Pinia.

Exemplo:

stores/auth.ts
Tipos

Interfaces TypeScript.

Exemplo:

types/book.ts
🧪 Build para produção

Build Gerar:

npm run build

Os arquivos serão gerados em:

dist/
📝 Melhorias futuras

Possíveis evoluções:

Dockerização do frontend
Deploy em ambiente cloud
Paginação visual com controles de página
Componentização dos cards de livros
Sistema global de mensagens
Testes automatizados
👨 💻 Autor

Lucas Dias

Projeto desenvolvido como desafio técnico Full-Stack utilizando Vue.js + Spring Boot.