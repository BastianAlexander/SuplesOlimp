package cl.duoc.resena_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"id", "producto", "cliente", "comentario", "calificacion", "fechaCreacion"})
public class ResenaDTO {

    private Long id;
    private ProductoDTO producto;
    private ClienteDTO cliente;
    private String comentario;
    private Integer calificacion;
    private LocalDateTime fechaCreacion;
}