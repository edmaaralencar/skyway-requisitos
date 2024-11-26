package com.cesar.trabalho.dtos;

import com.cesar.trabalho.enums.ClassType;

public class ReservarVooRequestDTO {
    private Long clienteId;
    private Long vooId;
    private Long assentoId;
    private Float preco;
    private ClassType classe;

    public Long getClienteId() {
        return clienteId;
    }

    public Long getVooId() {
        return vooId;
    }

    public Long getAssentoId() {
        return assentoId;
    }

    public Float getPreco() {
        return preco;
    }

    public ClassType getClasse() {
        return classe;
    }
}
