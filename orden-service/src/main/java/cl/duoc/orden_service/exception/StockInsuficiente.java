package cl.duoc.orden_service.exception;

public class StockInsuficiente extends RuntimeException {
    public StockInsuficiente(String message) {
        super(message);
    }
}
