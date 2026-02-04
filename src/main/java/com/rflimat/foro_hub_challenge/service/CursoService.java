package com.rflimat.foro_hub_challenge.service;

import com.rflimat.foro_hub_challenge.common.exception.ValidacionException;
import com.rflimat.foro_hub_challenge.dto.curso.DatosActualizacionCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosDetalleCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosListaCurso;
import com.rflimat.foro_hub_challenge.dto.curso.DatosRegistroCurso;
import com.rflimat.foro_hub_challenge.model.Curso;
import com.rflimat.foro_hub_challenge.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso registrar(DatosRegistroCurso datos) {
        if(cursoRepository.existsByNombre(datos.nombre())){
            throw new ValidacionException("Ya existe un curso con el nombre ingresado");
        }

        var curso = new Curso(null, datos);
        cursoRepository.save(curso);

        return new DatosDetalleCurso(curso);
    }

    public Page<DatosListaCurso> listar(Pageable paginacion) {
        var page = cursoRepository.findAll(paginacion).map(DatosListaCurso::new);
        return page;
    }

    public DatosDetalleCurso detallar(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return new DatosDetalleCurso(curso);
    }

    public DatosDetalleCurso actualizar(Long id, DatosActualizacionCurso datos) {
        if(id != null && !cursoRepository.existsById(id)){
            throw new ValidacionException("No existe un curso con el id informado");
        }

        if(cursoRepository.existsByNombreAndIdNot(datos.nombre(), id)){
            throw new ValidacionException("Ya existe un curso con el nombre ingresado");
        }

        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarInformacion(datos);

        return new DatosDetalleCurso(curso);
    }

    public void eliminar(Long id) {
        if(id != null && !cursoRepository.existsById(id)){
            throw new ValidacionException("No existe un curso con el id informado");
        }

        cursoRepository.deleteById(id);
    }
}
