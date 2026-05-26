CREATE TABLE resena (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        producto_id BIGINT NOT NULL,
                        cliente_id BIGINT NOT NULL,
                        comentario VARCHAR(255) NOT NULL,
                        calificacion INT NOT NULL,
                        fecha_creacion DATETIME NOT NULL
);