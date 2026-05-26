INSERT INTO bd_orden_service.orden
(cliente_id, carrito_id, total, estado, fecha_creacion)
VALUES
    (2, 2, 49990, 'PENDIENTE', NOW());

INSERT INTO bd_orden_service.orden_item
(orden_id, producto_id, cantidad, precio_unitario, subtotal)
VALUES
    (LAST_INSERT_ID(), 1, 1, 49990, 49990);