package com.cesar.trabalho.controllers;

import com.cesar.trabalho.jpa.core.assento.AssentoJpa;
import com.cesar.trabalho.jpa.core.assento.AssentoJpaRepositorio;
import com.cesar.trabalho.jpa.core.voo.VooJpa;
import com.cesar.trabalho.jpa.core.voo.VooJpaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {
    @Autowired
    private AssentoJpaRepositorio assentoJpaRepositorio;

    @GetMapping("/{id}")
    public ResponseEntity<Object> visualizarAssentosDeVoo(@PathVariable("id") String id) {
        List<AssentoJpa> assentos = assentoJpaRepositorio.findManyByVooId(Long.valueOf(id));

        return ResponseEntity.status(200).body(assentos);
    }
}
