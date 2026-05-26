package cl.duoc.auth_service.exception;

public class UsuarioNoExiste extends RuntimeException {
    public UsuarioNoExiste(String message) {
        super(message);
    }
}
