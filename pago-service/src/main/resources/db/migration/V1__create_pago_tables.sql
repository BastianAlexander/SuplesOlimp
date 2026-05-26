CREATE TABLE pago (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      orden_id BIGINT NOT NULL,
                      monto INT NOT NULL,
                      metodo_pago VARCHAR(50) NOT NULL,
                      estado VARCHAR(50) NOT NULL,
                      fecha_pago DATETIME NOT NULL
);