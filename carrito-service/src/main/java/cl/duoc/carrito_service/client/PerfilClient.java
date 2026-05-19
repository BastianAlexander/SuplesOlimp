package cl.duoc.carrito_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "perfil-service")
public interface PerfilClient {

    @GetMapping("/perfiles/{id}")
    Object buscarPerfilPorId(@PathVariable Long id);
}