package com.cesar.trabalho.dtos;

import com.cesar.trabalho.enums.ClassType;

import java.util.Optional;

public class ReservarVooRequestDTO {
    private Long clienteId;
    private Long vooId;
    private Long assentoId;
    private Float preco;
    private ClassType classe;
    private Optional<Long> desconto;

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

    public Optional<Long> getDesconto() {
        return desconto;
    }

    public void setDesconto(Optional<Long> desconto) {
        this.desconto = desconto;
    }
}
