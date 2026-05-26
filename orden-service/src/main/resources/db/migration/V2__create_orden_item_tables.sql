
CREATE TABLE orden_item (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            orden_id BIGINT NOT NULL,
                            producto_id BIGINT NOT NULL,
                            cantidad INT NOT NULL,
                            precio_unitario INT NOT NULL,
                            subtotal INT NOT NULL
);