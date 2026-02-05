package com.rflimat.foro_hub_challenge.controller;

import com.rflimat.foro_hub_challenge.dto.usuario.DatosActualizacionUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosListaUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosRegistroUsuario;
import com.rflimat.foro_hub_challenge.service.UsuarioService;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Transactional
    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listar(@PageableDefault(size=10) Pageable paginacion) {
        Pageable paginacionx = PageRequest.of(
                paginacion.getPageNumber(),
                paginacion.getPageSize(),
                JpaSort.unsafe(Sort.Direction.ASC, "nombre")
        );

        var page = service.listar(paginacionx);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = service.registrar(datos);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var usuario = service.detallar(id);
        return ResponseEntity.ok(usuario);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionUsuario datos) {
        var usuario = service.actualizar(id, datos);
        return ResponseEntity.ok(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
