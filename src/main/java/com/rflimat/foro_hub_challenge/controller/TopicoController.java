package com.rflimat.foro_hub_challenge.controller;

import com.rflimat.foro_hub_challenge.dto.topico.DatosRegistroTopico;
import com.rflimat.foro_hub_challenge.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService service;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var topico = service.registrar(datos);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.idUsuario()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }
}
