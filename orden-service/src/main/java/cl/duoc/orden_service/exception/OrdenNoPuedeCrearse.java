package cl.duoc.orden_service.exception;

public class OrdenNoPuedeCrearse extends RuntimeException {
    public OrdenNoPuedeCrearse(String message) {
        super(message);
    }
}
