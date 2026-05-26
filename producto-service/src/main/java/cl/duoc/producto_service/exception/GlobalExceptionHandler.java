package cl.duoc.producto_service.exception;

import cl.duoc.producto_service.exception.ErrorResponse;
import cl.duoc.producto_service.exception.CategoriaNoExiste;
import cl.duoc.producto_service.exception.ProductoNoExiste;
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

    @ExceptionHandler(CategoriaNoExiste.class)
    public ResponseEntity<?> errorValidacion(CategoriaNoExiste ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en los Datos ",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoNoExiste.class)
    public ResponseEntity<?> errorValidacion(ProductoNoExiste ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en los Datos ",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
