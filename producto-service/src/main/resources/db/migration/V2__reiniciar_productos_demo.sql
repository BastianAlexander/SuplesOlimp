SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE productos;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO productos (nombre, descripcion, marca, precio, activo) VALUES
                                                                       ('Creatina Monohidratada', 'Creatina para fuerza y rendimiento', 'Optimum Nutrition', 24990, 1),
                                                                       ('Proteína Whey', 'Proteína de suero para recuperación muscular', 'Muscletech', 34990, 1),
                                                                       ('Pre Entreno C4', 'Pre entreno para energía y enfoque', 'Cellucor', 29990, 1),
                                                                       ('BCAA', 'Aminoácidos para recuperación muscular', 'Universal Nutrition', 19990, 1),
                                                                       ('Glutamina', 'Suplemento para recuperación', 'Muscletech', 15990, 1),
                                                                       ('Omega 3', 'Ácidos grasos esenciales', 'Now Foods', 12990, 1),
                                                                       ('Multivitamínico Hombre', 'Vitaminas y minerales diarios', 'Centrum', 11990, 1),
                                                                       ('Cafeína', 'Cápsulas de cafeína para energía', 'Nutrex', 9990, 1),
                                                                       ('Caseína', 'Proteína de absorción lenta', 'Dymatize', 37990, 1),
                                                                       ('Shaker Deportivo', 'Botella mezcladora para suplementos', 'SmartShake', 7990, 1);