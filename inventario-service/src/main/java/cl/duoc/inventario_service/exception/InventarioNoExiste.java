package cl.duoc.inventario_service.exception;

public class InventarioNoExiste extends RuntimeException {
    public InventarioNoExiste(String message) {
        super(message);
    }
}
