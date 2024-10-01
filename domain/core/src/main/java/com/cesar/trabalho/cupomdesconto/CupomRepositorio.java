package com.cesar.trabalho.cupomdesconto;

import java.util.Optional;

public interface CupomRepositorio {
    Cupom salvar(Cupom cupom);
    Optional<Cupom> encontrarPorCodigo(String codigo);

}
