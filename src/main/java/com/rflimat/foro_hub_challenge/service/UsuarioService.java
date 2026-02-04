package com.rflimat.foro_hub_challenge.service;

import com.rflimat.foro_hub_challenge.common.exception.ValidacionException;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosActualizacionUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosDetalleUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosListaUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosRegistroUsuario;
import com.rflimat.foro_hub_challenge.model.Perfil;
import com.rflimat.foro_hub_challenge.model.Usuario;
import com.rflimat.foro_hub_challenge.repository.PerfilRepository;
import com.rflimat.foro_hub_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private Set<Perfil> verificarPerfiles(Set<String> perfiles) {
        return perfiles.stream()
                .map(nombre ->
                        perfilRepository.findByNombre(nombre).orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado: " + nombre)))
                .collect(Collectors.toSet());
    }

    public DatosDetalleUsuario registrar(DatosRegistroUsuario datos) {
        if(usuarioRepository.existsByCorreoElectronico(datos.correoElectronico())){
            throw new ValidacionException("Ya existe un usuario con el correo electronico ingresado");
        }

        Set<Perfil> perfiles = verificarPerfiles(datos.perfiles());
        String contrasenaCifrada = passwordEncoder.encode(datos.contrasena());
        var usuario = new Usuario(null, datos, contrasenaCifrada, perfiles);
        usuarioRepository.save(usuario);

        return new DatosDetalleUsuario(usuario);
    }

    public Page<DatosListaUsuario> listar(Pageable paginacion) {
        var page = usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new);
        return page;
    }

    public DatosDetalleUsuario detallar(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return new DatosDetalleUsuario(usuario);
    }

    public DatosDetalleUsuario actualizar(Long id, DatosActualizacionUsuario datos) {
        if(id != null && !usuarioRepository.existsById(id)){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        if(usuarioRepository.existsByCorreoElectronicoAndIdNot(datos.correoElectronico(), id)){
            throw new ValidacionException("Ya existe un usuario con el correo electronico ingresado");
        }

        Set<Perfil> perfiles = verificarPerfiles(datos.perfiles());

        String contrasenaCifrada = null;
        if (datos.contrasena() != null && !datos.contrasena().isBlank()) {
            contrasenaCifrada = passwordEncoder.encode(datos.contrasena());
        }

        var usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarInformacion(datos, contrasenaCifrada, perfiles);

        return new DatosDetalleUsuario(usuario);
    }

    public void eliminar(Long id) {
        if(id != null && !usuarioRepository.existsById(id)){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        usuarioRepository.deleteById(id);
    }
}
