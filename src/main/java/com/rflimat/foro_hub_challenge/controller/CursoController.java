package com.rflimat.foro_hub_challenge.controller;

import com.rflimat.foro_hub_challenge.dto.curso.DatosActualizacionCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosListaCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosRegistroCurso;
import com.rflimat.foro_hub_challenge.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService service;

    @Transactional
    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listar(@PageableDefault(size=10) Pageable paginacion) {
        Pageable paginationx = PageRequest.of(
                paginacion.getPageNumber(),
                paginacion.getPageSize(),
                JpaSort.unsafe(Sort.Direction.DESC, "nombre")
        );

        var page = service.listar(paginationx);
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
