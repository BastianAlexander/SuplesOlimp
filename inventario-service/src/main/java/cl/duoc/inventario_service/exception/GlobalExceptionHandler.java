package cl.duoc.inventario_service.exception;

import cl.duoc.inventario_service.exception.ErrorResponse;
import cl.duoc.inventario_service.exception.InventarioNoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .toList();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en validaciones",
                errores.toString(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InventarioNoExiste.class)
    public ResponseEntity<?> errorValidacion(InventarioNoExiste ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en los Datos ",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
