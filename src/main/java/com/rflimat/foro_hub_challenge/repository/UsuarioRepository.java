package com.rflimat.foro_hub_challenge.repository;

import com.rflimat.foro_hub_challenge.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    UserDetails findByCorreoElectronico(String correoElectronico);
}
