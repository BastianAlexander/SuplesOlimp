package cl.duoc.orden_service.client;

import cl.duoc.orden_service.dto.ProductoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "producto-service")
public interface ProductoClient {

    @GetMapping("/productos/{id}")
    ProductoResponse buscarProductoPorId(@PathVariable Long id);
}