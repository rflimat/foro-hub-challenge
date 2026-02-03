package com.rflimat.foro_hub_challenge.service;

import com.rflimat.foro_hub_challenge.common.exception.ValidacionException;
import com.rflimat.foro_hub_challenge.dto.topico.DatosActualizacionTopico;
import com.rflimat.foro_hub_challenge.dto.topico.DatosDetalleTopico;
import com.rflimat.foro_hub_challenge.dto.topico.DatosListaTopico;
import com.rflimat.foro_hub_challenge.dto.topico.DatosRegistroTopico;
import com.rflimat.foro_hub_challenge.model.Topico;
import com.rflimat.foro_hub_challenge.repository.CursoRepository;
import com.rflimat.foro_hub_challenge.repository.TopicoRepository;
import com.rflimat.foro_hub_challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {
        if(datos.idUsuario() != null && !usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        if(datos.idCurso() != null && !cursoRepository.existsById(datos.idCurso())){
            throw new ValidacionException("No existe un curso con el id informado");
        }

        if (topicoRepository.existsByTituloOrMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y/o mensaje");
        }

        var autor = usuarioRepository.findById(datos.idUsuario()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        var topico = new Topico(null, autor, curso, datos);
        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);
    }

    public Page<DatosListaTopico> listar(Pageable paginacion) {
        var page = topicoRepository.findAll(paginacion).map(DatosListaTopico::new);
        return page;
    }

    public DatosDetalleTopico detallar(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizar(Long id, DatosActualizacionTopico datos) {
        if(id != null && !topicoRepository.existsById(id)){
            throw new ValidacionException("No existe un topico con el id informado");
        }

        if (topicoRepository.existsByTituloAndMensajeAndIdNot(datos.titulo(), datos.mensaje(), id)) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y/o mensaje");
        }

        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarInformacion(datos);

        return new DatosDetalleTopico(topico);
    }
}
