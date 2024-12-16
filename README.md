# API Java para Dados de Séries para Frontend

Este projeto consiste em uma API RESTful desenvolvida em Java com Spring Boot, que busca dados de séries de uma API externa (OMDb) e os disponibiliza para consumo por <a href = https://github.com/manuverso/screenmatch-series-front>aplicação frontend</a>


## Visão Geral

A API foi construída utilizando as seguintes tecnologias 🔨:

*   **Java:** Linguagem de programação principal.
*   **Spring Boot:** Framework para desenvolvimento rápido de aplicações Java.
*   **Spring Web:** Módulo do Spring para criação de APIs REST.
*   **Spring JPA:** Framework de mapeamento objeto-relacional (ORM) com auxilio do Hibernate para o trabalho com persistência de dados 
*   **Banco de Dados (PostgreSQL):** Para persistência dos dados das séries.
*   **Cliente HTTP (ex: RestTemplate, WebClient):** Para consumir dados de APIs externas.
*   **Maven:** Gerenciador de dependências e build.

## Funcionalidades 🛠

*   **Listagem de Séries:** Retorna uma lista de todas as séries disponíveis.
*   **Busca por ID:** Retorna os detalhes de uma série específica, buscando pelo seu ID.
*   **Busca por Título/Nome:** Retorna uma lista de séries que correspondem ao termo de busca.
*   **(Opcional) Paginação:** Retorna os resultados de forma paginada para melhorar a performance com grandes quantidades de dados.
*   **(Opcional) Filtros:** Permite filtrar as séries por gênero, ano de lançamento, etc.

## Pré-requisitos

*   **JDK (Java Development Kit):** Versão 17 ou superior.
*   **Maven/Gradle:** Gerenciador de dependências.
*   **(Opcional) Banco de Dados:** Se for utilizar persistência em banco de dados.

## Como Executar

1.  **Clone o repositório:**

    ```bash
    git clone <url_do_repositorio>
    ```

2.  **Navegue até o diretório do projeto:**

    ```bash
    cd <nome_do_projeto>
    ```

3.  **(Opcional) Configure o banco de dados:** Se necessário, configure as credenciais de acesso ao banco de dados no arquivo `application.properties` ou `application.yml`.


A API estará disponível em `http://localhost:8080`.

## Endpoints da API (Exemplo)

*   `GET /series`: Retorna todas as séries.
*   `GET /series/top5`: Retorna as top 5 séries bem avaliadas.
*   `GET /series/{id}`: Busca séries pelo id.
*   `GET /series/categoria/{nomeGenero}`: Retorna as séries mediante ao gênero.

## Exemplo de Resposta (JSON)

```json
[
  {
    "id": 1,
    "nome": "Breaking Bad",
    "anoLancamento": 2008,
    "genero": "Drama",
    "descricao": "Um professor de química do ensino médio com câncer terminal se transforma em um produtor de metanfetamina para garantir o futuro financeiro de sua família.",
        "poster": "url_do_poster"
  },
  {
    "id": 2,
    "nome": "Stranger Things",
    "anoLancamento": 2016,
    "genero": "Ficção Científica",
    "descricao": "Quando um garoto desaparece, sua mãe, um chefe de polícia e seus amigos devem confrontar forças aterrorizantes para trazê-lo de volta.",
        "poster": "url_do_poster"
  }
]
