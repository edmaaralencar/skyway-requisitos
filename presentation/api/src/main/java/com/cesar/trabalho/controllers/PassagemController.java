package com.cesar.trabalho.controllers;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.dtos.ReservarVooRequestDTO;
import com.cesar.trabalho.dtos.TrocarVooRequestDTO;
import com.cesar.trabalho.jpa.core.assento.AssentoImpl;
import com.cesar.trabalho.jpa.core.passagem.PassagemImpl;
import com.cesar.trabalho.jpa.core.voo.VooImpl;
import com.cesar.trabalho.jpa.customers.cliente.ClienteImpl;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpaRepositorio;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemId;
import com.cesar.trabalho.passagem.PassagemServico;
import com.cesar.trabalho.voo.Voo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/passagens")
public class PassagemController {
    @Autowired
    private ClienteImpl clienteImpl;
    @Autowired
    private VooImpl vooImpl;
    @Autowired
    private AssentoImpl assentoImpl;
    @Autowired
    private PassagemImpl passagemImpl;

    @PostMapping("/reservar")
    public ResponseEntity<Object> reservarVoo(@RequestBody ReservarVooRequestDTO dto) {
        try {
            Cliente cliente = clienteImpl.encontrarPorId(dto.getClienteId()).orElseThrow(() -> new Exception("Usuário não encontrado"));
            Assento assento = assentoImpl.encontrarPorId(dto.getAssentoId()).orElseThrow(() -> new Exception("Assento não encontrado"));
            Voo voo = vooImpl.encontrarPorId(dto.getVooId()).orElseThrow(() -> new Exception("Voo não encontrado"));

            PassagemServico passagemServico = new PassagemServico(passagemImpl, assentoImpl, clienteImpl, vooImpl);

            Passagem passagem = passagemServico.reservarVoo(voo, assento, cliente, dto.getPreco(), dto.getClasse());

            return ResponseEntity.status(201).body(passagem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/trocar")
    public ResponseEntity<Object> trocarVoo(@RequestBody TrocarVooRequestDTO dto) {
        try {
            Cliente cliente = clienteImpl.encontrarPorId(dto.getClienteId()).orElseThrow(() -> new Exception("Usuário não encontrado"));
            Assento assento = assentoImpl.encontrarPorId(dto.getAssentoId()).orElseThrow(() -> new Exception("Assento não encontrado"));
            Voo voo = vooImpl.encontrarPorId(dto.getVooId()).orElseThrow(() -> new Exception("Voo não encontrado"));
            Passagem passagem = passagemImpl.encontrarPorId(new PassagemId((int)dto.getPassagemId()));

            PassagemServico passagemServico = new PassagemServico(passagemImpl, assentoImpl, clienteImpl, vooImpl);

            Passagem novaPassagem = passagemServico.trocarVoo(passagem, voo, assento, cliente, dto.getPreco(), dto.getClasse(), LocalDateTime.now());

            return ResponseEntity.status(201).body(passagem);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
