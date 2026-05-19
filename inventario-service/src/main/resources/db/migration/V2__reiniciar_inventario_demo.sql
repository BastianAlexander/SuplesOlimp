SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE inventario;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO inventario (producto_id, stock, activo) VALUES
                                                        (1, 50, 1),
                                                        (2, 40, 1),
                                                        (3, 30, 1),
                                                        (4, 35, 1),
                                                        (5, 25, 1),
                                                        (6, 60, 1),
                                                        (7, 45, 1),
                                                        (8, 70, 1),
                                                        (9, 20, 1),
                                                        (10, 55, 1);