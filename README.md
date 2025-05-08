
## 🧑‍💼 Módulo de Usuários

### Entidade: `User`
- `id: Long`
- `nome: String`
- `email: String`
- `senha: String`
- `tarefas: List<Tarefa>` — tarefas atribuídas ao usuário
- `projetos: List<Projeto>` — projetos nos quais o usuário está participando

### Endpoints (exemplos a serem definidos no controller):

| Método | Endpoint        | Descrição                   |
|--------|------------------|-----------------------------|
| GET    | `/users`         | Lista todos os usuários     |
| GET    | `/users/{id}`    | Retorna usuário por ID      |
| POST   | `/users`         | Cria um novo usuário        |
| DELETE | `/users/{id}`    | Deleta usuário por ID       |

> ⚠️ A verificação de e-mail duplicado é feita ao cadastrar novo usuário.

## 📌 Funcionalidades (em progresso)

- [x] Cadastro de usuários
- [x] Associação de tarefas a usuários
- [x] Associação de usuários a projetos
- [ ] Autenticação e autorização (em breve)
- [x] CRUD completo de projetos e tarefas


### Pré-requisitos
- Java 17+
- PostgreSQL
- Maven

### 1. Clone o projeto

```bash
git clone https://github.com/seu-usuario/gerenciador-tarefas-backend.git
cd gerenciador-tarefas-backend
