CREATE TABLE pagos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       orden_id BIGINT NOT NULL UNIQUE,
                       monto DECIMAL(10,2) NOT NULL,
                       metodo VARCHAR(50) NOT NULL,
                       estado VARCHAR(50) NOT NULL
);