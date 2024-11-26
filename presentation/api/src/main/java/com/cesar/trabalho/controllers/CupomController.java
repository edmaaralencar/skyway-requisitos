package com.cesar.trabalho.controllers;

import com.cesar.trabalho.cupomdesconto.Cupom;
import com.cesar.trabalho.cupomdesconto.CupomServico;
import com.cesar.trabalho.dtos.ValidarCupomRequestDTO;
import com.cesar.trabalho.jpa.core.cupom.CupomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cupons")
public class CupomController {
    @Autowired
    private CupomImpl cupomImpl;
    @PostMapping("/validar")
    public ResponseEntity<Object> validar(@RequestBody ValidarCupomRequestDTO dto) {
        try {
            CupomServico cupomServico = new CupomServico(cupomImpl);

            Cupom cupom = cupomServico.validarCodigo(dto.getCodigo());

            return ResponseEntity.status(200).body(cupom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
