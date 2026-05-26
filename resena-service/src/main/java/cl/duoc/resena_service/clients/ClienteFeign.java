package cl.duoc.resena_service.clients;

import cl.duoc.resena_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "perfil-service", path = "api/v1/perfiles")
public interface ClienteFeign {

    @GetMapping("/{id}")
    ClienteDTO buscarClientePorId(@PathVariable Long id);
}