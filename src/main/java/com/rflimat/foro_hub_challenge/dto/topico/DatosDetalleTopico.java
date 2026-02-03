package com.rflimat.foro_hub_challenge.dto.topico;

import com.rflimat.foro_hub_challenge.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Status status, String autor, String curso) {
    public DatosDetalleTopico (Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
