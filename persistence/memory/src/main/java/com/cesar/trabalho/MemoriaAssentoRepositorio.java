package com.cesar.trabalho;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoId;
import com.cesar.trabalho.assento.AssentoRepositorio;
import com.cesar.trabalho.voo.VooId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoriaAssentoRepositorio implements AssentoRepositorio {
    private final Map<AssentoId, Assento> assentos = new HashMap<>();

    @Override
    public Assento salvar(Assento assento) {
        this.assentos.put(assento.getId(), assento);
        return assento;
    }

    @Override
    public Assento encontrarPorNumeroEVoo(String numero, VooId vooId) {
        return null;
    }

    @Override
    public List<Assento> encontrarMuitosPorVoo(VooId vooId) {
        List<Assento> result = new ArrayList<>();
        for (Assento assento : assentos.values()) {
            if (assento.getVoo().getId().equals(vooId)) {
                result.add(assento);
            }
        }

        return result;
    }
}
