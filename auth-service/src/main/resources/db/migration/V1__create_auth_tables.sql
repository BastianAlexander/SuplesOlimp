CREATE TABLE usuarios_auth (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               correo VARCHAR(100) NOT NULL UNIQUE,
                               password VARCHAR(255) NOT NULL,
                               activo BOOLEAN NOT NULL DEFAULT TRUE,
                               fecha_creacion DATETIME NOT NULL
);