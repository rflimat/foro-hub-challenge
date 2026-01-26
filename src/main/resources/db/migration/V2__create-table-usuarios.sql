CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` BIGINT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `correoElectronico` VARCHAR(100) NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  `perfil_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),

  CONSTRAINT `fk_usuario_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `perfiles` (`id`)
);