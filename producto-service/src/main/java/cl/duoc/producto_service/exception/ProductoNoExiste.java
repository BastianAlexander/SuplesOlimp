package cl.duoc.producto_service.exception;

public class ProductoNoExiste extends RuntimeException {
    public ProductoNoExiste(String message) {
        super(message);
    }
}
