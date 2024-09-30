package com.cesar.trabalho.passagem;

public interface PassagemRepositorio {
    Passagem salvar(Passagem passagem);
    Passagem encontrarPorId(PassagemId id);
}
