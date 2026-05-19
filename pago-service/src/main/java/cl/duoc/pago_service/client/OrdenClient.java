package cl.duoc.pago_service.client;

import cl.duoc.pago_service.dto.OrdenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "orden-service")
public interface OrdenClient {

    @GetMapping("/ordenes/{id}")
    OrdenResponse buscarOrdenPorId(@PathVariable Long id);

    @PutMapping("/ordenes/{id}/estado")
    OrdenResponse actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    );
}