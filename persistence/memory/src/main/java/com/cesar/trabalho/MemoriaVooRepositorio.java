package com.cesar.trabalho;

import com.cesar.trabalho.voo.Voo;
import com.cesar.trabalho.voo.VooId;
import com.cesar.trabalho.voo.VooRepositorio;

import java.util.HashMap;
import java.util.Map;

public class MemoriaVooRepositorio implements VooRepositorio {
    private final Map<VooId, Voo> voos = new HashMap<>();

    @Override
    public Voo salvar(Voo voo) {
        this.voos.put(voo.getId(), voo);
        return voo;
    }
}
