Feature: Aplicação de Cupons de Desconto com Códigos Específicos

  Scenario: Cupom válido
    Given que o passageiro possui um cupom de desconto "válido"
    When o passageiro insere o código do cupom durante a compra
    Then o sistema valida o cupom e aplica o desconto corretamente

  Scenario: Cupom expirado
    Given que o passageiro possui um cupom de desconto "expirado"
    When o passageiro insere o código do cupom durante a compra
    Then o sistema rejeita o cupom e exibe uma mensagem informando que o cupom "expirou"

  Scenario: Cupom inválido
    Given que o passageiro possui um cupom de desconto "inválido"
    When o passageiro insere o código do cupom durante a compra
    Then o sistema rejeita o cupom e exibe uma mensagem informando que o cupom "é inválido"