package cl.duoc.carrito_service.exception;

public class CarritoItemNoExiste extends RuntimeException {
    public CarritoItemNoExiste(String message) {
        super(message);
    }
}
