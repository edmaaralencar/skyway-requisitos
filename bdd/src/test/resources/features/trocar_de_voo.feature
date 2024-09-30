Feature: Gestão de Mudanças de Voo

  Scenario: Alteração de voo com diferença de tarifa
    Given que o passageiro deseja alterar o voo
    When o passageiro escolhe um voo "com assentos disponíveis"
    Then o sistema calcula a diferença de tarifa
    And o passageiro seleciona um novo assento no voo alterado
    And o passageiro confirma o pagamento adicional antes da alteração ser confirmada

  Scenario: Voo alternativo sem diferença de tarifa
    Given que o passageiro deseja alterar o voo
    When o passageiro escolhe um voo "alternativo sem diferença de tarifa"
    Then o passageiro seleciona um novo assento no voo alterado
    And a alteração é confirmada sem custo adicional