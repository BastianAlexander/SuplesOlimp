package cl.duoc.pago_service.dto;

import lombok.Data;

@Data
public class OrdenDTO {

    private Long id;
    private Integer total;
    private String estado;
}