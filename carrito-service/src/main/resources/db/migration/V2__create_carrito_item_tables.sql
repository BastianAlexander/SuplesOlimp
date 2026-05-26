CREATE TABLE carrito_item (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              carrito_id BIGINT NOT NULL,
                              producto_id BIGINT NOT NULL,
                              cantidad INT NOT NULL
);