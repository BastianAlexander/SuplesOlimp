package cl.duoc.orden_service.clients;

import cl.duoc.orden_service.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventario-service", path = "api/v1/inventario")
public interface InventarioFeign {

    @GetMapping("/producto/{productoId}")
    InventarioDTO buscarPorProductoId(@PathVariable Long productoId);

    @PutMapping("/{id}/disminuir/{cantidad}")
    InventarioDTO disminuirStock(@PathVariable Long id, @PathVariable Integer cantidad);
}