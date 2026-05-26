INSERT INTO bd_pago_service.pago
(orden_id, monto, metodo_pago, estado, fecha_pago)
VALUES
    (
        (
            SELECT id
            FROM bd_orden_service.orden
            WHERE carrito_id = 2
            ORDER BY id DESC
            LIMIT 1
    ),
    49990,
    'DEBITO',
    'PAGADO',
    NOW()
    );