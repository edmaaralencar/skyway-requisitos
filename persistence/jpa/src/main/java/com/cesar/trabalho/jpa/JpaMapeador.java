package com.cesar.trabalho.jpa;

import com.cesar.trabalho.assento.Assento;
import com.cesar.trabalho.assento.AssentoId;
import com.cesar.trabalho.cliente.Cliente;
import com.cesar.trabalho.cliente.ClienteId;
import com.cesar.trabalho.cupomdesconto.Cupom;
import com.cesar.trabalho.cupomdesconto.CupomId;
import com.cesar.trabalho.jpa.core.assento.AssentoJpa;
import com.cesar.trabalho.jpa.core.cupom.CupomJpa;
import com.cesar.trabalho.jpa.core.passagem.PassagemJpa;
import com.cesar.trabalho.jpa.core.reembolso.ReembolsoJpa;
import com.cesar.trabalho.jpa.core.voo.EscalaJpa;
import com.cesar.trabalho.jpa.core.voo.VooJpa;
import com.cesar.trabalho.jpa.customers.cliente.ClienteIdConverter;
import com.cesar.trabalho.jpa.customers.cliente.ClienteJpa;
import com.cesar.trabalho.passagem.Passagem;
import com.cesar.trabalho.passagem.PassagemId;
import com.cesar.trabalho.reembolso.Reembolso;
import com.cesar.trabalho.reembolso.ReembolsoId;
import com.cesar.trabalho.voo.Escala;
import com.cesar.trabalho.voo.Voo;
import com.cesar.trabalho.voo.VooId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaMapeador extends ModelMapper {
    JpaMapeador() {
        var configuracao = getConfiguration();
        configuracao.setFieldMatchingEnabled(true);
        configuracao.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        ClienteIdConverter clienteIdConverter = new ClienteIdConverter();

        addConverter(new AbstractConverter<Cliente, ClienteJpa>() {
            @Override
            protected ClienteJpa convert(Cliente source) {
                ClienteJpa clienteJpa = new ClienteJpa();
                clienteJpa.setCpf(source.getCpf());
                clienteJpa.setNome(source.getNome());
                clienteJpa.setEmail(source.getEmail());
                clienteJpa.setSaldo(source.getCredito().getSaldo());
                return clienteJpa;
            }
        });

        addConverter(new AbstractConverter<ClienteJpa, Cliente>() {
            @Override
            protected Cliente convert(ClienteJpa source) {
                Cliente cliente = new Cliente(new ClienteId((int)source.getId()), source.getNome(), source.getCpf(), source.getEmail(), source.getSaldo());
                return cliente;
            }
        });

        addConverter(new AbstractConverter<Cupom, CupomJpa>() {
            @Override
            protected CupomJpa convert(Cupom source) {
                CupomJpa cupomJpa = new CupomJpa();

                cupomJpa.setId((long)source.getId().getId());
                cupomJpa.setCodigo(source.getCodigo());
                cupomJpa.setValorDesconto(source.getValorDesconto());
                cupomJpa.setValidade(source.getValidade());

                return cupomJpa;
            }
        });

        addConverter(new AbstractConverter<CupomJpa, Cupom>() {
            @Override
            protected Cupom convert(CupomJpa source) {
                Cupom cupom = new Cupom();

                cupom.setValidade(source.getValidade());
                cupom.setCodigo(source.getCodigo());
                cupom.setValorDesconto(source.getValorDesconto());
                cupom.setId(new CupomId((int)source.getId()));

                return cupom;
            }
        });

        addConverter(new AbstractConverter<Reembolso, ReembolsoJpa>() {
            @Override
            protected ReembolsoJpa convert(Reembolso source) {
                ReembolsoJpa reembolsoJpa = new ReembolsoJpa();

                reembolsoJpa.setId((long)source.getId().getId());
                reembolsoJpa.setMetodo(source.getMetodo());
                reembolsoJpa.setValor(source.getValor());
                reembolsoJpa.setDataRecebimento(source.getDataRecebimento());

                reembolsoJpa.setPassagem(map(source.getPassagem(), PassagemJpa.class));
                reembolsoJpa.setCliente(map(source.getCliente(), ClienteJpa.class));

                return reembolsoJpa;
            }
        });

        addConverter(new AbstractConverter<ReembolsoJpa, Reembolso>() {
            @Override
            protected Reembolso convert(ReembolsoJpa source) {
                Reembolso reembolso = new Reembolso();

                reembolso.setId(new ReembolsoId(source.getId().intValue()));
                reembolso.setMetodo(source.getMetodo());
                reembolso.setValor(source.getValor());
                reembolso.setDataRecebimento(source.getDataRecebimento());

                reembolso.setPassagem(map(source.getPassagem(), Passagem.class));
                reembolso.setCliente(map(source.getCliente(), Cliente.class));

                return reembolso;
            }
        });


        addConverter(new AbstractConverter<Passagem, PassagemJpa>() {
            @Override
            protected PassagemJpa convert(Passagem source) {
                PassagemJpa passagemJpa = new PassagemJpa();

                passagemJpa.setClasse(source.getClasse());
                passagemJpa.setPreco(source.getPreco());
                passagemJpa.setDataCompra(source.getDataCompra());
                passagemJpa.setStatus(source.getStatus());

                passagemJpa.setVoo(map(source.getVoo(), VooJpa.class));
                passagemJpa.setAssento(map(source.getAssento(), AssentoJpa.class));
                passagemJpa.setCliente(map(source.getCliente(), ClienteJpa.class));

                return passagemJpa;
            }
        });

        addConverter(new AbstractConverter<PassagemJpa, Passagem>() {
            @Override
            protected Passagem convert(PassagemJpa source) {
                Passagem passagem = new Passagem();

                passagem.setId(new PassagemId((int)source.getId()));

                passagem.setClasse(source.getClasse());
                passagem.setPreco(source.getPreco());
                passagem.setDataCompra(source.getDataCompra());
                passagem.setStatus(source.getStatus());

                passagem.setVoo(map(source.getVoo(), Voo.class));
                passagem.setAssento(map(source.getAssento(), Assento.class));
                passagem.setCliente(map(source.getCliente(), Cliente.class));

                return passagem;
            }
        });

        addConverter(new AbstractConverter<Assento, AssentoJpa>() {
            @Override
            protected AssentoJpa convert(Assento source) {
                AssentoJpa assentoJpa = new AssentoJpa();

                assentoJpa.setId(source.getId().getId());
                assentoJpa.setNumero(source.getNumero());
                assentoJpa.setEstaDisponivel(source.isEstaDisponivel());

                if (source.getVoo() != null && source.getVoo().getId() != null) {
                    assentoJpa.setVoo(map(source.getVoo(), VooJpa.class));
                } else {
                    assentoJpa.setVoo(null); // ou lance uma exceção, dependendo do caso
                }


                return assentoJpa;
            }
        });

        addConverter(new AbstractConverter<AssentoJpa, Assento>() {
            @Override
            protected Assento convert(AssentoJpa source) {
                Assento assento = new Assento();

                assento.setEstaDisponivel(source.isEstaDisponivel());
                assento.setId(new AssentoId((int)source.getId()));
                assento.setNumero(source.getNumero());

                /*assento.setVoo(map(source.getVoo(), Voo.class));*/

                if (source.getVoo() != null) {
                    Voo voo = new Voo();
                    voo.setId(new VooId((int) source.getVoo().getId()));
                    assento.setVoo(voo);
                }



                return assento;
            }
        });


        addConverter(new AbstractConverter<Voo, VooJpa>() {
            @Override
            protected VooJpa convert(Voo source) {
                VooJpa vooJpa = new VooJpa();

                vooJpa.setId(source.getId().getId());

                List<EscalaJpa> escalas = source.getEscalas() != null
                        ? source.getEscalas().stream()
                        .map(escala -> map(escala, EscalaJpa.class))
                        .toList()
                        : List.of();
                List<AssentoJpa> assentos = source.getAssentos() != null
                        ? source.getAssentos().stream()
                        .map(assento -> map(assento, AssentoJpa.class))
                        .toList()
                        : List.of();
                vooJpa.setEscalas(escalas);
                vooJpa.setDestino(source.getDestino());
                vooJpa.setHorarioChegada(source.getHorarioChegada());
                vooJpa.setStatus(source.getStatus());
                vooJpa.setNumero(source.getNumero());
                vooJpa.setOrigem(source.getOrigem());
                vooJpa.setHorarioPartida(source.getHorarioPartida());
                vooJpa.setAssentos(assentos);

                return vooJpa;
            }
        });

        addConverter(new AbstractConverter<VooJpa, Voo>() {
            @Override
            protected Voo convert(VooJpa source) {

                List<Escala> escalas = source.getEscalas() != null
                        ? source.getEscalas().stream()
                        .map(escala -> map(escala, Escala.class))
                        .toList()
                        : List.of();

                List<Assento> assentos = source.getAssentos() != null
                        ? source.getAssentos().stream()
                        .map(assento -> map(assento, Assento.class))
                        .toList()
                        : List.of();

                Voo voo = new Voo(
                        source.getNumero(),
                        source.getOrigem(),
                        source.getDestino(),
                        escalas,
                        source.getHorarioPartida(),
                        source.getHorarioChegada(),
                        source.getStatus()
                );

                voo.setAssentos(assentos);
                voo.setId(new VooId((int)source.getId()));

                return voo;
            }
        });

        addConverter(new AbstractConverter<Escala, EscalaJpa>() {
            @Override
            protected EscalaJpa convert(Escala source) {
                EscalaJpa escalaJpa = new EscalaJpa();

                escalaJpa.setAeroporto(source.getAeroporto());
                escalaJpa.setHorarioChegada(source.getHorarioChegada());
                escalaJpa.setHorarioPartida(source.getHorarioPartida());

                return escalaJpa;
            }
        });

        addConverter(new AbstractConverter<EscalaJpa, Escala>() {
            @Override
            protected Escala convert(EscalaJpa source) {
                Escala escala = new Escala(source.getAeroporto(), source.getHorarioPartida(), source.getHorarioChegada());

                return escala;
            }
        });
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }
}
