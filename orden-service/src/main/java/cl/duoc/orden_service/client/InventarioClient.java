package cl.duoc.orden_service.client;

import cl.duoc.orden_service.dto.DescontarStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventario-service")
public interface InventarioClient {

    @PutMapping("/inventario/producto/{productoId}/descontar")
    Object descontarStock(
            @PathVariable Long productoId,
            @RequestBody DescontarStockRequest request
    );
}