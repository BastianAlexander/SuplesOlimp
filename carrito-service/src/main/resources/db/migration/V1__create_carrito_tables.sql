CREATE TABLE carrito_items (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               perfil_id BIGINT NOT NULL,
                               producto_id BIGINT NOT NULL,
                               cantidad INT NOT NULL,
                               activo BOOLEAN NOT NULL DEFAULT TRUE
);