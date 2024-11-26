package com.cesar.trabalho.passagem;

public interface PassagemRepositorio {
    Passagem salvar(Passagem passagem, String type);
    Passagem encontrarPorId(PassagemId id);
}
