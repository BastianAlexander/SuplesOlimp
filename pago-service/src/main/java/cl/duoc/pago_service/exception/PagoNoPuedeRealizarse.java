package cl.duoc.pago_service.exception;

public class PagoNoPuedeRealizarse extends RuntimeException {
    public PagoNoPuedeRealizarse(String message) {
        super(message);
    }
}
