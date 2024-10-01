package com.cesar.trabalho.reembolso;

public interface ReembolsoRepositorio {
    Reembolso salvar(Reembolso reembolso);
    Reembolso encontrarPorId(ReembolsoId id);
}
