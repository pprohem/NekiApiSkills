## 📚 NEKI SKILLS - Projeto Teste 

### A API foi desenvolvida em Java 17 e Spring Boot 3.0.0 seguindo o padrão Restful.

#### Com autenticação JWT, determinados endpoints só podem ser acessados com o token gerado no login do usuário. 

#### A aplicação conta também com o swagger OpenApi que documenta tudo de forma mais fácil e dinâmica, trazendo informações de possíveis erros e também como objetos de requisição devem ser preenchidos. 

#### A aplicação está em teste e recebendo atualizações, futuramente contará também com refreshToken.

### Endpoints

#### /login
Este endpoint é usado para fazer login na API. Requer um nome de usuário e uma senha válidos e retorna um token JWT para ser usado nas solicitações subsequentes.

Método: `POST`

Parâmetros:

`username` (string): nome de usuário
`password` (string): senha

Retorno:

`token` (string): token JWT


### /user

Este endpoint é usado para gerenciar usuários. Permite criar, ler, atualizar e excluir usuários.

- `GET`: Obter informações do usuário
- `POST`: Criar um novo usuário
- `PUT`: Atualizar informações do usuário
- `DELETE`: Excluir um usuário


#### /skill
Este endpoint é usado para gerenciar as habilidades disponíveis para o usuário consumir. Permite criar, ler, atualizar e excluir habilidades.

Métodos:

- `GET`: Obter informações de habilidades
- `GET BY ID`: Obter informações da habilidade específica. 
- `POST`: Criar uma nova habilidade
- `PUT`: Atualizar informações de habilidades
- `DELETE`: Excluir uma habilidade


#### /userskill
Este endpoint é usado para gerenciar as habilidades que um usuário possui. Permite criar, ler, atualizar e excluir habilidades do usuário em questão. 

Métodos: 

- `GET`: Obter todas as habilidades e os usuários linkados a elas 
- `GET BY ID`: Obter informações da habilidade específica. 
- `POST`: Criar uma nova habilidade
- `PUT`: Atualizar informações de habilidades de usuário.
- `DELETE`: Excluir uma habilidade de usuário. 
