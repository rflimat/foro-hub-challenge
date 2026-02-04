package com.rflimat.foro_hub_challenge.model;

import com.rflimat.foro_hub_challenge.dto.curso.Categoria;
import com.rflimat.foro_hub_challenge.dto.curso.DatosActualizacionCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosRegistroCurso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(Long id, DatosRegistroCurso datosRegistroCurso) {
        this.id = id;
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = Categoria.fromString(datosRegistroCurso.categoria());
    }

    public void actualizarInformacion(@Valid DatosActualizacionCurso datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.categoria() != null) {
            this.categoria = Categoria.fromString(datos.categoria());
        }
    }
}
