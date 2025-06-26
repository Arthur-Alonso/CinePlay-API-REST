# CinePlay API REST 🎬

API RESTful para gerenciamento de filmes, sessões de cinema e compras de ingressos. Desenvolvida com Java e Spring Boot.

## 🚀 Funcionalidades

- 📽️ Cadastro, listagem, busca, atualização e deleção lógica de filmes
- 🕒 Cadastro, atualização condicional, listagem, compra e deleção lógica de sessões
- ✅ Regras de negócio como:
  - Impedir alterações de data/preço após vendas
  - Compra de ingressos com controle de vagas disponíveis
  - Exclusão lógica (soft delete) com controle de reativação

## 🛠️ Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Swagger (Documentação da API)
- Maven

## 📄 Exemplos de Endpoints

### Filmes

- `POST /filmes` → Cadastrar filme
- `GET /filmes` → Listar filmes ativos
- `GET /filmes/buscar?titulo=batman` → Buscar filme por título
- `PUT /filmes` → Atualizar filme
- `DELETE /filmes/{id}` → Desativar filme
- `PUT /filmes/{id}/reativar` → Reativar filme

### Sessões

- `POST /sessoes` → Cadastrar sessão
- `GET /sessoes` → Listar sessões
- `GET /sessoes/buscar?data=2025-07-01` → Buscar sessões por data
- `PUT /sessoes/{id}/comprar?quantidade=3` → Comprar ingressos
- `PUT /sessoes` → Atualizar sessão
- `DELETE /sessoes/{id}` → Desativar sessão
- `PUT /sessoes/{id}/reativar` → Reativar sessão

## ✅ Regras de Atualização de Sessão

- **Sem vendas:** Pode alterar qualquer coisa, exceto o filme e ID.
- **Com vendas:** Só pode alterar `sala` e `tipo de exibição`.

## 🧪 Testes

Você pode testar a API utilizando ferramentas como:

- Postman
- Swagger UI 

## 🧠 Autor

Desenvolvido por [Arthur Alonso](https://github.com/Arthur-Alonso) 🚀
