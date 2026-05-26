package cl.duoc.carrito_service.exception;

public class CarritoNoExiste extends RuntimeException {
    public CarritoNoExiste(String message) {
        super(message);
    }
}
