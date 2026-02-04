package com.rflimat.foro_hub_challenge.model;

import com.rflimat.foro_hub_challenge.dto.topico.DatosActualizacionTopico;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosActualizacionUsuario;
import com.rflimat.foro_hub_challenge.dto.usuario.DatosRegistroUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String correoElectronico;
    private String contrasena;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_perfiles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<Perfil> perfiles = new HashSet<>();

    public Usuario(Long id, DatosRegistroUsuario datosRegistroUsuario, String contrasenaCifrada, Set<Perfil> perfiles) {
        this.id = id;
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = contrasenaCifrada;
        this.perfiles = perfiles;
    }

    public void actualizarInformacion(@Valid DatosActualizacionUsuario datos, String contrasenaCifrada, Set<Perfil> perfiles) {
        if (datos.nombre() != null && !datos.nombre().isBlank()) {
            this.nombre = datos.nombre();
        }
        if (datos.correoElectronico() != null && !datos.correoElectronico().isBlank()) {
            this.correoElectronico = datos.correoElectronico();
        }
        if (datos.contrasena() != null && !datos.contrasena().isBlank()) {
            this.contrasena = contrasenaCifrada;
        }
        if (perfiles != null) {
            this.perfiles = perfiles;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfiles.stream()
                .map(p -> new SimpleGrantedAuthority(p.getNombre()))
                .toList();
    }

    @Override
    public @Nullable String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
