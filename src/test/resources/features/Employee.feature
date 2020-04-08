#Author: gilmarrodriguesst@gmail.com
@ApiChallenge
Feature: Realizar Cadastro e

  Scenario: Cadastrar um novo empregado
    When realizo o cadastro de um novo empregado
      | nome    | Gilmar Rodrigues |
      | salario |              500 |
      | idade   |               24 |
    Then valido os dados retornados no cadastrado

    Scenario: Consultar o empregado cadastrado
    When consulto o empregado cadastrado
    Then valido os dados do empregado
    
    Scenario: Excluir empregado
    Then excluo o empregado cadastrado
    
    