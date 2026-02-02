CREATE TABLE IF NOT EXISTS `usuarios_perfiles` (
  `usuario_id` BIGINT NOT NULL,
  `perfil_id` BIGINT NOT NULL,

  CONSTRAINT `fk_usuarios_perfiles_perfiles1` FOREIGN KEY (`perfil_id`)  REFERENCES `perfiles` (`id`),
  CONSTRAINT `fk_usuarios_perfiles_usuarios1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
);