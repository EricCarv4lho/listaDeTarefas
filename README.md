# Projeto Spring Boot - ToDo List

Este projeto é uma aplicação Spring Boot que permite gerenciar uma lista de tarefas (ToDo List). A aplicação possibilita a criação, listagem, atualização e exclusão de tarefas, utilizando o banco de dados MySQL para persistência dos dados.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para interações com bancos de dados utilizando JPA (Java Persistence API).
- **Spring Web**: Para construção de APIs RESTful.
- **Spring Validation**: Para validação de dados de entrada.
- **Springdoc OpenAPI**: Para documentação da API com Swagger.
- **MySQL**: Banco de dados relacional utilizado para persistência de dados.

## Como Executar

### Clone o Repositório
```bash
git clone https://github.com/EricCarv4lho/listaDeTarefas.git

```
## A documentação da API está disponível em: 
http://localhost:8080/swagger-ui.html
## Funcionalidades

- **Criar Tarefa**: Permite adicionar novas tarefas à lista.
- **Listar Tarefas**: Exibe todas as tarefas cadastradas.
- **Atualizar Tarefa**: Possibilita a edição de informações de tarefas existentes.
- **Excluir Tarefa**: Remove tarefas da lista.

## Estrutura do Projeto

### Entidades

- **Tarefa**: Representa uma tarefa com detalhes como título e descrição.

## Endpoints

### Adicionar Tarefa

- **Endpoint**: `/tarefas`
- **Método**: `POST`
- **Descrição**: Adiciona uma nova tarefa à lista.
- **Corpo da Requisição**:
  ```json
  {
    "title": "Título da tarefa",
    "description": "Descrição da tarefa"
  }


## Respostas da API

### Adicionar Tarefa
- **Resposta**:
  - **Status**: `201 Created`

### Listar Todas as Tarefas
- **Endpoint**: `/tarefas/listar`
- **Método**: `GET`
- **Descrição**: Retorna uma lista de todas as tarefas cadastradas.
- **Resposta**:
  - **Status**: `200 OK`
  - **Body** (JSON):
    ```json
    [
      {
        "id": 1,
        "title": "Título da tarefa 1",
        "description": "Descrição da tarefa 1"
      }
    ]
    ```

### Listar Uma Tarefa
- **Endpoint**: `/tarefas/listar/{id}`
- **Método**: `GET`
- **Descrição**: Retorna os detalhes de uma tarefa específica com base no seu ID.
- **Parâmetros**:
  - `id` (path): ID da tarefa a ser buscada.
- **Resposta**:
  - **Status**: `200 OK`
  - **Body** (JSON):
    ```json
    {
      "id": 1,
      "title": "Título da tarefa",
      "description": "Descrição da tarefa"
    }
    ```

### Deletar Tarefa
- **Endpoint**: `/tarefas/{id}`
- **Método**: `DELETE`
- **Descrição**: Remove uma tarefa da lista com base no seu ID.
- **Parâmetros**:
  - `id` (path): ID da tarefa a ser deletada.
- **Resposta**:
  - **Status**: `200 OK`

### Atualizar Tarefa
- **Endpoint**: `/tarefas/{id}`
- **Método**: `PUT`
- **Descrição**: Atualiza as informações de uma tarefa existente.
- **Parâmetros**:
  - `id` (path): ID da tarefa a ser atualizada.
- **Corpo da Requisição**:
  ```json
  {
    "title": "Novo título da tarefa",
    "description": "Nova descrição da tarefa"
  }

 
- **Resposta**:
  - **Status**: `200 OK`
  - **Body** (JSON):
    ```json
    {
      "id": 1,
      "title": "Novo título da tarefa",
      "description": "Nova descrição da tarefa"
    }
    ```


    
