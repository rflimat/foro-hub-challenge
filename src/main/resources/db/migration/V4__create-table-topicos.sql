CREATE TABLE IF NOT EXISTS `topicos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `mensaje` TEXT NOT NULL,
  `fechaCreacion` DATETIME NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `autor_id` BIGINT NOT NULL,
  `curso_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_topicos_usuarios` FOREIGN KEY (`autor_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_topicos_cursos` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id`)
);