package com.rflimat.foro_hub_challenge.dto.usuario;

import java.util.Set;

public record DatosActualizacionUsuario(
        String nombre,
        String correoElectronico,
        String contrasena,
        Set<String> perfiles
) {
}
