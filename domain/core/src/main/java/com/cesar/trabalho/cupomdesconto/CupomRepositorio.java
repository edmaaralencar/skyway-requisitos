package com.cesar.trabalho.cupomdesconto;

import com.cesar.trabalho.models.DiscountCoupon;

import java.util.Optional;

public interface CupomRepositorio {
    Cupom salvar(Cupom cupom);
    Optional<Cupom> encontrarPorCodigo(String codigo);

}
