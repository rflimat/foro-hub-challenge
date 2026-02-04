package com.rflimat.foro_hub_challenge.controller;

import com.rflimat.foro_hub_challenge.dto.curso.DatosActualizacionCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosListaCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosRegistroCurso;
import com.rflimat.foro_hub_challenge.service.CursoService;
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
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService service;

    @Transactional
    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listar(@PageableDefault(size=10, sort={"nombre"}, direction = Sort.Direction.ASC) Pageable paginacion) {
        var page = service.listar(paginacion);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder) {
        var curso = service.registrar(datos);

        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.id()).toUri();

        return ResponseEntity.created(uri).body(curso);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var curso = service.detallar(id);
        return ResponseEntity.ok(curso);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionCurso datos) {
        var curso = service.actualizar(id, datos);
        return ResponseEntity.ok(curso);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
