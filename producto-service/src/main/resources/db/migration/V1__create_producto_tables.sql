CREATE TABLE productos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion VARCHAR(255),
                           marca VARCHAR(100) NOT NULL,
                           precio DECIMAL(10,2) NOT NULL,
                           activo BOOLEAN NOT NULL DEFAULT TRUE
);