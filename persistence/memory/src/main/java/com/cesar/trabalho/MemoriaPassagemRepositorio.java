package com.cesar.trabalho;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteId;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemId;
import com.cesar.trabalho.passagem.PassagemRepositorio;
import com.cesar.trabalho.passagem.PassagemServico;

import java.util.HashMap;
import java.util.Map;

public class MemoriaPassagemRepositorio implements PassagemRepositorio {
    private final Map<PassagemId, Passagem> passagens = new HashMap<>();

    @Override
    public Passagem salvar(Passagem passagem) {
        this.passagens.put(passagem.getId(), passagem);
        return passagem;
    }

    @Override
    public Passagem encontrarPorId(PassagemId id) {
        return this.passagens.get(id);
    }
}
