package com.cesar.trabalho.dtos;


public class SolicitarReembolsoRequestDTO {
    private long passagemId;
    private Long clienteId;
    public Long getClienteId() {
        return clienteId;
    }

    public long getPassagemId() {
        return passagemId;
    }

}
