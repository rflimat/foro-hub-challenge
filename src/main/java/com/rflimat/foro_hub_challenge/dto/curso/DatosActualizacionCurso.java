package com.rflimat.foro_hub_challenge.dto.curso;

import org.hibernate.validator.constraints.UniqueElements;

public record DatosActualizacionCurso(
        String nombre,
        String categoria
) {
}
