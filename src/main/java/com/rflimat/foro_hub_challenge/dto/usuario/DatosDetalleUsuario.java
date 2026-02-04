package com.rflimat.foro_hub_challenge.dto.usuario;

import com.rflimat.foro_hub_challenge.model.Perfil;
import com.rflimat.foro_hub_challenge.model.Usuario;

import java.util.List;

public record DatosDetalleUsuario(Long id, String nombre, String correoElectronico, List<String> perfiles) {
    public DatosDetalleUsuario (Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfiles().stream().map(Perfil::getNombre).toList());
    }
}
