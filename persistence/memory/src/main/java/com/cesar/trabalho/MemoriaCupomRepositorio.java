package com.cesar.trabalho;

import com.cesar.trabalho.cupomdesconto.Cupom;
import com.cesar.trabalho.cupomdesconto.CupomId;
import com.cesar.trabalho.cupomdesconto.CupomRepositorio;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoriaCupomRepositorio implements CupomRepositorio {
    private final Map<CupomId, Cupom> cupons = new HashMap<>();

    @Override
    public Cupom salvar(Cupom cupom) {
        this.cupons.put(cupom.getId(), cupom);
        return cupom;
    }

    @Override
    public Optional<Cupom> encontrarPorCodigo(String codigo) {
        return this.cupons.values().stream()
                .filter(cupon -> cupon.getCodigo().equals(codigo))
                .findFirst();
    }
}
