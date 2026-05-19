package cl.duoc.orden_service.client;

import cl.duoc.orden_service.dto.CarritoItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carrito-service")
public interface CarritoClient {

    @GetMapping("/carrito/perfil/{perfilId}")
    List<CarritoItemResponse> listarPorPerfil(@PathVariable Long perfilId);

    @DeleteMapping("/carrito/perfil/{perfilId}")
    void vaciarCarrito(@PathVariable Long perfilId);
}