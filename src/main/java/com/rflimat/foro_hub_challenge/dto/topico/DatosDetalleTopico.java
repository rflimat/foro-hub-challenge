package com.rflimat.foro_hub_challenge.dto.topico;

import com.rflimat.foro_hub_challenge.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, Status status, Long idUsuario, Long idCurso) {
    public DatosDetalleTopico (Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
