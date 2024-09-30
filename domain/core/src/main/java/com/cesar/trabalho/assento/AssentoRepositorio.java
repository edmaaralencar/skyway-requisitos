package com.cesar.trabalho.assento;

import com.cesar.trabalho.models.Seat;
import com.cesar.trabalho.voo.VooId;

import java.util.List;

public interface AssentoRepositorio {
    Assento salvar(Assento assento);
    Assento encontrarPorNumeroEVoo(String numero, VooId vooId);
    List<Assento> encontrarMuitosPorVoo(VooId vooId);
}
