package cl.duoc.orden_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-service", contextId = "carritoItemFeign", path = "api/v1/carrito-items")
public interface CarritoItemFeign {

    @DeleteMapping("/carrito/{carritoId}/vaciar")
    void vaciarCarrito(@PathVariable Long carritoId);
}