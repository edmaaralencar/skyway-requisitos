package com.cesar.trabalho.controllers;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.dtos.CriarClienteRequestDTO;
import com.cesar.trabalho.jpa.customers.cliente.ClienteImpl;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteImpl clienteImpl;

    @Autowired
    private ClienteJpaRepositorio clienteJpaRepositorio;

    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody CriarClienteRequestDTO dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), dto.getEmail(), Float.valueOf("50000.0"));

        Cliente clienteCriado = clienteImpl.salvar(cliente);

        return ResponseEntity.status(200).body(clienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> verCliente(@PathVariable("id") String id) {

        Optional<ClienteJpa> cliente = clienteJpaRepositorio.findById(Long.valueOf(id));

        return ResponseEntity.status(200).body(cliente.get());
    }
}
