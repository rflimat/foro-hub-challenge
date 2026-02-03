package com.rflimat.foro_hub_challenge.repository;

import com.rflimat.foro_hub_challenge.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloOrMensaje(String titulo, String mensaje);
}
