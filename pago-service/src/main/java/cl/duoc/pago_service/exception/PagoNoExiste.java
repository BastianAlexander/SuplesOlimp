package cl.duoc.pago_service.exception;

public class PagoNoExiste extends RuntimeException {
    public PagoNoExiste(String message) {
        super(message);
    }
}
