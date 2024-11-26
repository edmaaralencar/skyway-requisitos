package com.cesar.trabalho.controllers;

import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.dtos.CriarClienteRequestDTO;
import com.cesar.trabalho.jpa.customers.cliente.ClienteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteImpl clienteImpl;

    @PostMapping
    public ResponseEntity<Object> cadastrarCliente(@RequestBody CriarClienteRequestDTO dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), dto.getEmail(), Float.valueOf("50000.0"));

        Cliente clienteCriado = clienteImpl.salvar(cliente);

        return ResponseEntity.status(200).body(clienteCriado);
    }
}
