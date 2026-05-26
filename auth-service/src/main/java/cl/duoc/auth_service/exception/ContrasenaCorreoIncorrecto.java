package cl.duoc.auth_service.exception;

public class ContrasenaCorreoIncorrecto extends RuntimeException {
    public ContrasenaCorreoIncorrecto(String message) {
        super(message);
    }
}
