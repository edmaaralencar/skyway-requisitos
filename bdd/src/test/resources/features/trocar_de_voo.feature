Feature: Gestão de Mudanças de Voo com Taxas

  Scenario: Alteração de voo com diferença de tarifa e taxas
    Given que o passageiro deseja alterar o voo
    When o passageiro escolhe um voo
    And o voo é de classe "Executiva"
    And a alteração é feita com "menos de 24 horas"
    Then o sistema calcula as taxas
    And o sistema confirma a alteração "com as taxas"

  Scenario: Voo alternativo sem diferença de tarifa e sem taxa
    Given que o passageiro deseja alterar o voo
    When o passageiro escolhe um voo
    And o voo é de classe "Economica"
    And a alteração é feita com "mais de 72 horas"
    Then o sistema calcula as taxas
    And o sistema confirma a alteração "sem custo adicional"

