CREATE TABLE resenas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         perfil_id BIGINT NOT NULL,
                         producto_id BIGINT NOT NULL,
                         puntuacion INT NOT NULL,
                         comentario VARCHAR(500),
                         fecha DATETIME NOT NULL,
                         activo BOOLEAN NOT NULL DEFAULT TRUE
);