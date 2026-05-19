CREATE TABLE perfiles (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          usuario_auth_id BIGINT NOT NULL UNIQUE,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          telefono VARCHAR(20),
                          direccion VARCHAR(255),
                          activo BOOLEAN NOT NULL DEFAULT TRUE
);