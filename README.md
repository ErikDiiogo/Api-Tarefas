# API de Tarefas — REST API com Autenticação

API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas com autenticação JWT.

## Funcionalidades
- Autenticação com Basic Auth e geração de token JWT
- Criar tarefa com título, descrição, prioridade e prazo
- Listar todas as tarefas
- Buscar tarefa por ID
- Atualizar tarefa existente
- Deletar tarefa por ID
- Rotas protegidas por token JWT

## Como funciona a autenticação

Usuário envia credenciais (username + password)
↓
Spring Security valida no banco de dados
↓
API retorna token JWT assinado com chave RSA
↓
Usuário usa o token pra acessar rotas protegidas

## Endpoints

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| POST | /authenticate | Gerar token JWT | Basic Auth |
| POST | /tarefas | Criar tarefa | Bearer Token |
| GET | /tarefas | Listar todas | Bearer Token |
| GET | /tarefas/{id} | Buscar por ID | Bearer Token |
| PUT | /tarefas/{id} | Atualizar tarefa | Bearer Token |
| DELETE | /tarefas/{id} | Deletar tarefa | Bearer Token |

## Exemplo de uso

### 1. Autenticar e obter token

POST http://localhost:8080/authenticate
Authorization: Basic Auth
Username: username
Password: password


### 2. Usar o token nas requisições

GET http://localhost:8080/tarefas
Authorization: Bearer {token_jwt}


### 3. Criar tarefa
```json
POST http://localhost:8080/tarefas
Authorization: Bearer {token_jwt}

{
    "titulo": "Estudar Spring Boot",
    "descricao": "Ver aulas e praticar",
    "status": "PENDENTE",
    "prioridade": "ALTA",
    "dataLimite": "2025-12-31"
}
```

## Como executar
1. Clone o repositório
2. Gere as chaves RSA com Git Bash:
```bash
openssl genrsa -out app.key 2048
openssl rsa -in app.key -pubout -out app.pub
```
3. Mova os arquivos pra `src/main/resources/`
4. Configure o banco no `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_tarefas
spring.datasource.username=postgres
spring.datasource.password=sua_senha
jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key
```
5. Crie o banco e insira um usuário:
```sql
CREATE DATABASE api_tarefas;
INSERT INTO users (username, password) VALUES ('username', 'hash_bcrypt');
```
6. Execute:
```bash
mvn spring-boot:run
```

## Tecnologias
- Java 21
- Spring Boot 4.1.1
- Spring Security
- JWT com chaves RSA
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Conceitos aplicados
- Autenticação com Basic Auth
- Geração de token JWT com chaves RSA
- Autorização com Bearer Token
- BCrypt para criptografia de senha
- UserDetailsService customizado
- SecurityFilterChain configurado
- CRUD completo com Spring Data JPA
