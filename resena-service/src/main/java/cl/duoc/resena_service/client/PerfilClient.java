package cl.duoc.resena_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "perfil-service")
public interface PerfilClient {

    @GetMapping("/perfiles/{id}")
    Object buscarPerfilPorId(@PathVariable Long id);
}