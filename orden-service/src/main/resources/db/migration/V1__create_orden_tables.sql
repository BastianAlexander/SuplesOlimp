CREATE TABLE orden (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       cliente_id BIGINT NOT NULL,
                       carrito_id BIGINT NOT NULL,
                       total INT NOT NULL,
                       estado VARCHAR(50) NOT NULL,
                       fecha_creacion DATETIME NOT NULL
);