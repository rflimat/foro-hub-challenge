package com.rflimat.foro_hub_challenge.controller;

import com.rflimat.foro_hub_challenge.dto.topico.DatosActualizacionTopico;
import com.rflimat.foro_hub_challenge.dto.topico.DatosListaTopico;
import com.rflimat.foro_hub_challenge.dto.topico.DatosRegistroTopico;
import com.rflimat.foro_hub_challenge.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size=10, sort={"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable paginacion) {
        var page = service.listar(paginacion);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var topico = service.registrar(datos);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = service.detallar(id);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = service.actualizar(id, datos);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
