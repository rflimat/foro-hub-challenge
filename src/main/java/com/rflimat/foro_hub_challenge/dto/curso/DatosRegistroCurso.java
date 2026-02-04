package com.rflimat.foro_hub_challenge.dto.curso;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record DatosRegistroCurso(
        @NotNull String nombre,
        @NotNull String categoria
) {
}
