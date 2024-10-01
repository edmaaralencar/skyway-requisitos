Feature: Cancelamento de Passagem com Política de Reembolso

  Scenario: Cancelamento com reembolso parcial
    Given que o passageiro deseja cancelar a passagem "dentro do prazo para reembolso"
    When o passageiro solicita o cancelamento
    Then o sistema calcula o valor do reembolso com base na política de cancelamento
    And o sistema credita o valor aprovado na conta do passageiro

  Scenario: Cancelamento sem reembolso
    Given que o passageiro deseja cancelar a passagem "fora do prazo para reembolso"
    When o passageiro solicita o cancelamento
    Then o sistema informa que não há reembolso disponível
    And o cancelamento é concluído sem restituição
