package com.cesar.trabalho.controllers;

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
@RequestMapping("/api/voos")
public class VooController {
    @Autowired
    private VooJpaRepositorio vooJpaRepositorio;

    @GetMapping
    public ResponseEntity<Object> visualizarVoos() {
        List<VooJpa> voos = vooJpaRepositorio.findAll();

        return ResponseEntity.status(200).body(voos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> visualizarVoo(@PathVariable("id") String id) {
        Optional<VooJpa> voo = vooJpaRepositorio.findById(Long.valueOf(id));

        return ResponseEntity.status(200).body(voo);
    }

}
