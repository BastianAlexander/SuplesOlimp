package cl.duoc.orden_service.clients;

import cl.duoc.orden_service.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-service", contextId = "carritoFeign", path = "api/v1/carritos")
public interface CarritoFeign {

    @GetMapping("/{id}")
    CarritoDTO buscarCarritoPorId(@PathVariable Long id);
}