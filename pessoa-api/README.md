#PESSOAS-API

Simples crud para a manutenção de dados (_alunos_, _professores_ e _recepcionistas_) de uma academia

## Regras de negócio do sistema

 - Um CPF só pode ser cadastrado uma vez no sistema;
 - Uma pessoa menor de 18 anos não pode ser cadastrada no sistema;
 - A matrícula das pessoas é gerada automaticamente pelo sistema com a seguinte sintaxe: "Primeira letra do tipo" + "Ano de cadastro" + "Um número aleatório entre 0 e 50000";
 - O dia do pagamento tem que ser preenchido de acordo com o número maximo de dias no mes, ou seja, 31;

## Tecnologias 

- JAVA
- Spring Boot
- Migrations (Flyway)
- MySql
- Swagger

## Como rodar a API

 - Rodar um banco MySql (localmente ou com o auxilio do docker) contendo a seguinte dados:
    + username = root
    + password = root
    + port = 33306
    + database = pessoasdb
 - Rodar o _PessoaApiApplication_ com auxilio da IDE ou pelo terminal com o comando "mvn spring-boot:run";
 - Para acessar os endpoints é necessario do auxilio do Postman ou algum frameork semelhante ou apenas abrir o link do swwagger que esta configurado na aplicação (http://localhost:8080/swagger-ui.html).

## Créditos

- [Lucas Lima Leite](https://github.com/lucasleite300)