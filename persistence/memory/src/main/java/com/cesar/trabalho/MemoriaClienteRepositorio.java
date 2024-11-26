package com.cesar.trabalho;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteId;
import com.cesar.trabalho.cliente.ClienteRepositorio;

import java.util.HashMap;
import java.util.Map;

public class MemoriaClienteRepositorio implements ClienteRepositorio {
    private final Map<ClienteId, Cliente> customers = new HashMap<>();

    @Override
    public Cliente salvar(Cliente cliente) {
        this.customers.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        this.customers.put(cliente.getId(), cliente);
        return cliente;
    }
}
