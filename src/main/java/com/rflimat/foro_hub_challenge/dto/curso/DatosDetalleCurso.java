package com.rflimat.foro_hub_challenge.dto.curso;


import com.rflimat.foro_hub_challenge.model.Curso;

public record DatosDetalleCurso (Long id, String nombre, Categoria categoria) {
    public DatosDetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
