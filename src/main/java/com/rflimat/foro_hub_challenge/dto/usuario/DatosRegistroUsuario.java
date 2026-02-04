package com.rflimat.foro_hub_challenge.dto.usuario;

import com.rflimat.foro_hub_challenge.model.Perfil;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DatosRegistroUsuario(
        @NotNull String nombre,
        @NotNull String correoElectronico,
        @NotNull String contrasena,
        Set<String> perfiles
) {
}
