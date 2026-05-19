package cl.duoc.perfil_service.controller;

import cl.duoc.perfil_service.dto.ActualizarPerfilRequest;
import cl.duoc.perfil_service.dto.CrearPerfilRequest;
import cl.duoc.perfil_service.model.Perfil;
import cl.duoc.perfil_service.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    public ResponseEntity<Perfil> crear(@Valid @RequestBody CrearPerfilRequest request) {
        return ResponseEntity.ok(perfilService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioAuthId}")
    public ResponseEntity<Perfil> buscarPorUsuarioAuthId(@PathVariable Long usuarioAuthId) {
        return ResponseEntity.ok(perfilService.buscarPorUsuarioAuthId(usuarioAuthId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarPerfilRequest request
    ) {
        return ResponseEntity.ok(perfilService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        perfilService.eliminar(id);
        return ResponseEntity.ok("Perfil eliminado correctamente");
    }

    @PutMapping("/{id}/direccion")
    public ResponseEntity<Perfil> actualizarDireccion(
            @PathVariable Long id,
            @RequestParam String direccion
    ) {
        return ResponseEntity.ok(perfilService.actualizarDireccion(id, direccion));
    }

    @PutMapping("/{id}/telefono")
    public ResponseEntity<Perfil> actualizarTelefono(
            @PathVariable Long id,
            @RequestParam String telefono
    ) {
        return ResponseEntity.ok(perfilService.actualizarTelefono(id, telefono));
    }
}