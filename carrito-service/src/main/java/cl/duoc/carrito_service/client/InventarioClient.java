package cl.duoc.carrito_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "inventario-service")
public interface InventarioClient {

    @GetMapping("/inventario/producto/{productoId}")
    Map<String, Object> buscarInventarioPorProductoId(@PathVariable Long productoId);
}