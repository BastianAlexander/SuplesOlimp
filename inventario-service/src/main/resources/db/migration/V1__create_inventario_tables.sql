CREATE TABLE inventario (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            producto_id BIGINT NOT NULL UNIQUE,
                            stock INT NOT NULL
);