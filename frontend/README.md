# BookManager Frontend

Frontend da aplicação **BookManager**, desenvolvido utilizando Vue.js 3 e TypeScript.

Este módulo é responsável pela interface gráfica da aplicação, permitindo que usuários interajam com o sistema através de uma aplicação web moderna e responsiva.

O frontend realiza comunicação com o Backend Spring Boot através de uma API REST utilizando HTTP/JSON.


---

# 1. Tecnologias Utilizadas


## Framework

- Vue.js 3


## Linguagem

- TypeScript


## Build Tool

- Vite


## Comunicação HTTP

- Axios


## Interface

- HTML5
- CSS3


## Gerenciamento de Dependências

- npm



---

# 2. Arquitetura do Frontend


O frontend segue uma arquitetura baseada em componentes:


             Usuário

                |

                |

         Vue Application

                |

                |

          Components

                |

                |

          Services/API

                |

                |

      Backend Spring Boot API



Responsabilidades:


## Components

Responsáveis por:

- Construção das telas;
- Interação com usuário;
- Exibição dos dados.


## Services

Responsáveis por:

- Comunicação com API;
- Envio de requisições HTTP;
- Tratamento de respostas.


## Stores / Estado

Responsáveis por:

- Controle de dados compartilhados;
- Informações do usuário autenticado.


---

# 3. Estrutura do Projeto



Frontend

│

├── src

│ │

│ ├── ativos

│ │

│ ├── Componentes

│ │

│ ├── Vistas

│ │

│ ├── Serviços

│ │

│ ├── roteador

│ │

│ └── main.ts

│

├── público

│

├── package.json

├── vite.config.ts

└── README.md




---

# 4. Pré-requisitos


Antes de executar o frontend instale:


## Node.js


Versão recomendada:



Node.js 20+



Verificar instalação:


```bash
node -v

Exemplo:

v20.x.x
NPM

Verificar:

npm -v
5. Instalação das Dependências

Entrar na pasta frontend:

cd frontend

Instalar dependências:

npm install

O comando irá instalar todas as bibliotecas definidas no arquivo:

package.json
6. Configuração da API Backend

O frontend depende do Backend Spring Boot.

Backend esperado:

http://localhost:8080

A URL da API deve ser configurada através de variável de ambiente.

Criar arquivo:

.env

Adicionar:

VITE_API_URL=http://localhost:8080

Exemplo:

frontend

│

├── .env

├── package.json

└── src

7. Executar Ambiente de Desenvolvimento

Executor:

npm run dev

Após iniciar, será exibido:

Local:
http://localhost:5173/

Acessar:

http://localhost:5173
8. Comunicação com Backend

O frontend realiza chamadas para a API REST.

Fluxo:

Usuário

   |

   |

Vue Component

   |

   |

Axios Service

   |

   |

Spring Boot API

   |

   |

PostgreSQL
9. Autenticação JWT

O sistema utiliza autenticação através de token JWT.

Fluxo:

Logar

Usuário informa:

{
    "email":"usuario@email.com",
    "password":"123456"
}

O retorno do backend:

{
    "token":"jwt_token"
}

Frontend O:

Armazena o token;
Envia o token nas próximas requisições;
Libera acesso às funcionalidades protegidas.

Formato enviado:

Authorization:

Bearer TOKEN
10. Funcionalidades Disponíveis
Usuário
Cadastro;
Login;
Controle de sessão.
Livros

Usuário autenticado pode:

Visualizar livros cadastrados;
Criar novos livros;
Editar livros;
Excluir livros;
Buscar informações.
11. Executar Build de Produção

Gerar arquivos otimizados:

npm run build

Resultado:

dist/

A pasta gerada contém os arquivos necessários para publicação.

12. Visualizar Build Localmente

Instalar servidor:

npm install -g serve

Executor:

serve -s dist

A aplicação estará disponível em:

http://localhost:3000
13. Comandos Principais
Instalar dependências
npm install
Executar desenvolvimento
npm run dev
Construção Gerar
npm run build
Verificar projeto
npm run lint
14. Executar a Frontend via Docker

Caso utilize o ambiente completo:

Na raiz do projeto:

docker compose up --build

O Docker será responsável por iniciar os serviços configurados.

15. Integração com Backend

Configuração esperada:

Frontend:

http://localhost:5173

Backend:

http://localhost:8080

Banco:

PostgreSQL 18
16. Solução de Problemas
Erro de conexão com API

Verificar:

Backend está iniciado;
Porta 8080 disponível;
Variável VITE_API_URL configurada.
Erro no npm install

Executor:

npm cache clean --force

Depois:

npm install
Porta 5173 ocupada

Executor:

npm run dev -- --port 5174
17. Decisões Técnicas
Vue.js 3

Escolhido por:

Componentização;
apresentação de jiboias;
Grande adoção no mercado;
Facilidade de manutenção.
TypeScript

Utilizado para:

Maior segurança durante desenvolvimento;
Melhor manutenção;
Redução de erros.
Vite

Escolhido por:

Inicialização rápida;
Build otimizado;
Excelente integração com Vue.
Axios

Utilizado para:

Comunicação HTTP;
Interceptação de requisições;
Integração com autenticação JWT.
Autor

Lucas Dias

Frontend desenvolvido como parte do desafio técnico Full Stack BookManager.