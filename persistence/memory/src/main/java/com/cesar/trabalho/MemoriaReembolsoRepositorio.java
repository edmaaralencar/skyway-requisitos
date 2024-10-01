package com.cesar.trabalho;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.reembolso.Reembolso;
import com.cesar.trabalho.reembolso.ReembolsoId;
import com.cesar.trabalho.reembolso.ReembolsoRepositorio;
import com.cesar.trabalho.reembolso.ReembolsoServico;

import java.util.HashMap;
import java.util.Map;

public class MemoriaReembolsoRepositorio implements ReembolsoRepositorio {
    private final Map<ReembolsoId, Reembolso> reembolsos = new HashMap<>();

    @Override
    public Reembolso salvar(Reembolso reembolso) {
        this.reembolsos.put(reembolso.getId(), reembolso);
        return reembolso;
    }

    @Override
    public Reembolso encontrarPorId(ReembolsoId id) {
        return this.reembolsos.get(id);
    }
}
