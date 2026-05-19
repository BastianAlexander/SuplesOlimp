package cl.duoc.orden_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DescontarStockRequest {

    private Integer cantidad;
}