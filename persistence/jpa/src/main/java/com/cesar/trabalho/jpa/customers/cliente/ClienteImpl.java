package com.cesar.trabalho.jpa.customers.cliente;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteRepositorio;
import com.cesar.trabalho.jpa.JpaMapeador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteImpl implements ClienteRepositorio {
    @Autowired
    private ClienteJpaRepositorio repositorio;

    @Autowired
    private JpaMapeador mapeador;

    public Optional<Cliente> encontrarPorId(Long id) {
        Optional<ClienteJpa> clienteJpa = repositorio.findById((long)id);

        return Optional.of(mapeador.map(clienteJpa, Cliente.class));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteJpa clienteJpa = mapeador.map(cliente, ClienteJpa.class);

        repositorio.save(clienteJpa);
        return mapeador.map(clienteJpa, Cliente.class);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        ClienteJpa clienteJpa = mapeador.map(cliente, ClienteJpa.class);

        clienteJpa.setId(cliente.getId().getId());

        repositorio.save(clienteJpa);
        return mapeador.map(clienteJpa, Cliente.class);
    }
}