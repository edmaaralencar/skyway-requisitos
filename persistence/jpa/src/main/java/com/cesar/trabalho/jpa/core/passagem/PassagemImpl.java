package com.cesar.trabalho.jpa.core.passagem;

import com.cesar.trabalho.jpa.JpaMapeador;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemId;
import com.cesar.trabalho.passagem.PassagemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PassagemImpl implements PassagemRepositorio {
    @Autowired
    private PassagemJpaRepositorio repositorio;

    @Autowired
    private JpaMapeador mapeador = JpaMapeador.getInstance();

    @Override
    public Passagem salvar(Passagem passagem, String type) {
        PassagemJpa passagemJpa = mapeador.map(passagem, PassagemJpa.class);

        ClienteJpa novoCliente = new ClienteJpa();
        novoCliente.setId(passagem.getCliente().getId().getId());
        novoCliente.setEmail(passagem.getCliente().getEmail());
        novoCliente.setNome(passagem.getCliente().getEmail());
        novoCliente.setCpf(passagem.getCliente().getCpf());
        novoCliente.setSaldo(passagem.getCliente().getCredito().getSaldo());

        passagemJpa.setCliente(novoCliente);

        if (type.equals("atualizar")) {
            passagemJpa.setId(passagem.getId().getId());
        }

        repositorio.save(passagemJpa);

        return passagem;
    }

    @Override
    public Passagem encontrarPorId(PassagemId id) {
        Optional<PassagemJpa> passagemJpa = repositorio.findById((long)id.getId());

        if (passagemJpa.isEmpty()) {
            return new Passagem();
        }

        return mapeador.map(passagemJpa, Passagem.class);
    }
}
