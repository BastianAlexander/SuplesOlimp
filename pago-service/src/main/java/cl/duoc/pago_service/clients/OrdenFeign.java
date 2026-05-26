package cl.duoc.pago_service.clients;

import cl.duoc.pago_service.dto.OrdenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "orden-service", path = "api/v1/ordenes")
public interface OrdenFeign {

    @GetMapping("/{id}")
    OrdenDTO buscarOrdenPorId(@PathVariable Long id);

    @PutMapping("/{id}/estado/{estado}")
    OrdenDTO cambiarEstado(@PathVariable Long id, @PathVariable String estado);
}