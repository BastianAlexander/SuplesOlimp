package cl.duoc.carrito_service.clients;

import cl.duoc.carrito_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", path = "api/v1/productos")
public interface ProductoFeign {

    @GetMapping("/{id}")
    ProductoDTO buscarProductoPorId(@PathVariable Long id);
}