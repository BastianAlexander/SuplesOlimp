CREATE TABLE ordenes (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         perfil_id BIGINT NOT NULL,
                         total DECIMAL(10,2) NOT NULL,
                         estado VARCHAR(50) NOT NULL,
                         fecha DATETIME NOT NULL
);

CREATE TABLE orden_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             orden_id BIGINT NOT NULL,
                             producto_id BIGINT NOT NULL,
                             cantidad INT NOT NULL,
                             precio_unitario DECIMAL(10,2) NOT NULL,
                             subtotal DECIMAL(10,2) NOT NULL,
                             CONSTRAINT fk_orden_items_orden
                                 FOREIGN KEY (orden_id) REFERENCES ordenes(id)
);