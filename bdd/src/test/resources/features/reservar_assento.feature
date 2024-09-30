Feature: Reserva de Assentos com Verificação de Disponibilidade
  Scenario: Assento específico disponível
    Given que o passageiro visualiza o mapa de assentos e seleciona um assento específico que está "disponível"
    When o passageiro "confirma" a reserva do assento
    Then a reserva do assento é "confirmada"
    And o assento selecionado fica indisponível para outros passageiros

  Scenario: Assento específico indisponível
    Given que o passageiro visualiza o mapa de assentos e seleciona um assento específico que está "ocupado"
    When o passageiro "tenta realizar" a reserva do assento
    Then a reserva do assento é "cancelada"
    And o passageiro é notificado que o assento já está ocupado

  Scenario: Saldo insuficiente para reserva de assento
    Given que o passageiro visualiza o mapa de assentos e seleciona um assento específico que está "disponível"
    When o passageiro "tenta realizar sem saldo suficiente" a reserva do assento
    Then a reserva do assento é "cancelada"
    And o passageiro é notificado que não tem saldo suficiente para a reserva
