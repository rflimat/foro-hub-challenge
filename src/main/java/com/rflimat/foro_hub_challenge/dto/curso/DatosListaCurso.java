package com.rflimat.foro_hub_challenge.dto.curso;

import com.rflimat.foro_hub_challenge.model.Curso;

public record DatosListaCurso (Long id, String nombre, Categoria categoria) {
    public DatosListaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
