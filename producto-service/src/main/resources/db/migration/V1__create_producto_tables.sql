CREATE TABLE producto
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    precio       INT          NOT NULL,
    descripcion  VARCHAR(150) NOT NULL,
    id_categoria BIGINT       NOT NULL

);

