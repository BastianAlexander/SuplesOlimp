package cl.duoc.perfil_service.clients;

import cl.duoc.perfil_service.dto.AuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", path = "api/v1/auth")
public interface AuthFeign {

    @GetMapping("/{id}")
    AuthDTO buscarAuthPorId(@PathVariable Long id);

    @GetMapping("/correo/{correo}")
    AuthDTO buscarPorCorreo(@PathVariable String correo);
}
