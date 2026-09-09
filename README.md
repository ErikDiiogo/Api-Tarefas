### Criar tarefa
```json
POST /tarefas
{
    "titulo": "Estudar Spring Boot",
    "descricao": "Ver aulas e praticar",
    "status": "PENDENTE",
    "prioridade": "ALTA",
    "dataLimite": "2025-12-31T23:59:59"
}
```

### Resposta
```json
{
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Ver aulas e praticar",
    "status": "PENDENTE",
    "prioridade": "ALTA",
    "dataCriacao": "2025-09-09T17:00:00",
    "dataLimite": "2025-12-31T23:59:59"
}
```

## Como executar
1. Clone o repositório
2. Configure o banco de dados no `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_tarefas
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```
3. Crie o banco no PostgreSQL:
```sql
CREATE DATABASE api_tarefas;
```
4. Execute o projeto:
```bash
mvn spring-boot:run
```
5. Acesse `http://localhost:8080`

## Tecnologias
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Conceitos aplicados
- API REST com Spring Boot
- JPA e Hibernate
- CRUD completo
- Lombok para redução de código
- Tratamento de exceções
- Conexão com banco de dados PostgreSQL
