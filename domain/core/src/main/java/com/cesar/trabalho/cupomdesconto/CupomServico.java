package com.cesar.trabalho.cupomdesconto;

import com.cesar.trabalho.models.DiscountCoupon;

import java.time.LocalDate;
import java.util.Optional;

public class CupomServico {
    private final CupomRepositorio cupomRepositorio;

    public CupomServico(CupomRepositorio cupomRepositorio) {
        this.cupomRepositorio = cupomRepositorio;
    }

    public Cupom validarCodigo(String code) throws Exception {
        Optional<Cupom> cupom = this.cupomRepositorio.encontrarPorCodigo(code);

        if (cupom.isEmpty()) {
            throw new Exception("Código não existe");
        }

        LocalDate now = LocalDate.now();

        if (now.isAfter(cupom.get().getValidade())) {
            throw new Exception("Código expirado");
        }

        return cupom.get();
    }
}
