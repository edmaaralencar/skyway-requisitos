package com.cesar.trabalho.controllers;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.dtos.SolicitarReembolsoRequestDTO;
import com.cesar.trabalho.dtos.TrocarVooRequestDTO;
import com.cesar.trabalho.jpa.core.assento.AssentoImpl;
import com.cesar.trabalho.jpa.core.passagem.PassagemImpl;
import com.cesar.trabalho.jpa.core.reembolso.ReembolsoImpl;
import com.cesar.trabalho.jpa.core.voo.VooImpl;
import com.cesar.trabalho.jpa.customers.cliente.ClienteImpl;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemId;
import com.cesar.trabalho.reembolso.Reembolso;
import com.cesar.trabalho.reembolso.ReembolsoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reembolsos")
public class ReembolsoController {
    @Autowired
    private ClienteImpl clienteImpl;
    @Autowired
    private VooImpl vooImpl;
    @Autowired
    private AssentoImpl assentoImpl;
    @Autowired
    private PassagemImpl passagemImpl;
    @Autowired
    private ReembolsoImpl reembolsoImpl;

    @PostMapping
    public ResponseEntity<Object> reembolsarVoo(@RequestBody SolicitarReembolsoRequestDTO dto) {
        try {
            Cliente cliente = clienteImpl.encontrarPorId(dto.getClienteId()).orElseThrow(() -> new Exception("Usuário não encontrado"));
            Passagem passagem = passagemImpl.encontrarPorId(new PassagemId((int)dto.getPassagemId()));

            ReembolsoServico reembolsoServico = new ReembolsoServico(passagemImpl, assentoImpl, clienteImpl, reembolsoImpl);

            Reembolso reembolso = reembolsoServico.solicitarReembolso(cliente, passagem);

            return ResponseEntity.status(201).body(reembolso);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
