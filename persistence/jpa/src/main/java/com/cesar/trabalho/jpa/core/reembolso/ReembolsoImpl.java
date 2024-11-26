package com.cesar.trabalho.jpa.core.reembolso;

import com.cesar.trabalho.jpa.JpaMapeador;
import com.cesar.trabalho.jpa.core.passagem.PassagemJpa;
import com.cesar.trabalho.jpa.core.passagem.PassagemJpaRepositorio;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.reembolso.Reembolso;
import com.cesar.trabalho.reembolso.ReembolsoId;
import com.cesar.trabalho.reembolso.ReembolsoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReembolsoImpl implements ReembolsoRepositorio {
    @Autowired
    private ReembolsoJpaRepositorio repositorio;

    @Autowired
    private JpaMapeador mapeador;

    @Override
    public Reembolso salvar(Reembolso reembolso) {
        ReembolsoJpa reembolsoJpa = mapeador.map(reembolso, ReembolsoJpa.class);

        reembolsoJpa.setId(null);

        ClienteJpa novoCliente = new ClienteJpa();
        novoCliente.setNome(reembolso.getCliente().getNome());
        novoCliente.setEmail(reembolso.getCliente().getEmail());
        novoCliente.setCpf(reembolso.getCliente().getCpf());
        novoCliente.setId(reembolso.getCliente().getId().getId());

        PassagemJpa novaPassagem = mapeador.map(reembolso.getPassagem(), PassagemJpa.class);
        novaPassagem.setId(reembolso.getPassagem().getId().getId());

        reembolsoJpa.setPassagem(novaPassagem);
        reembolsoJpa.setCliente(novoCliente);

        repositorio.save(reembolsoJpa);

        return reembolso;
    }

    @Override
    public Reembolso encontrarPorId(ReembolsoId id) {
        Optional<ReembolsoJpa> reembolsoJpa = repositorio.findById((long)id.getId());

        if (reembolsoJpa.isEmpty()) {
            return new Reembolso();
        }

        return mapeador.map(reembolsoJpa, Reembolso.class);
    }
}
