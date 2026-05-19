CREATE TABLE usuarios_auth (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               correo VARCHAR(100) NOT NULL UNIQUE,
                               password VARCHAR(255) NOT NULL,
                               activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuario_roles (
                               usuario_id BIGINT NOT NULL,
                               rol_id BIGINT NOT NULL,
                               PRIMARY KEY (usuario_id, rol_id),
                               CONSTRAINT fk_usuario_roles_usuario
                                   FOREIGN KEY (usuario_id) REFERENCES usuarios_auth(id),
                               CONSTRAINT fk_usuario_roles_rol
                                   FOREIGN KEY (rol_id) REFERENCES roles(id)
);

INSERT INTO roles (nombre) VALUES ('ADMIN');
INSERT INTO roles (nombre) VALUES ('CLIENTE');
INSERT INTO roles (nombre) VALUES ('OPERADOR');