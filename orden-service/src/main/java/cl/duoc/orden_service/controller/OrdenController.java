package cl.duoc.orden_service.controller;

import cl.duoc.orden_service.dto.CrearOrdenRequest;
import cl.duoc.orden_service.model.Orden;
import cl.duoc.orden_service.service.OrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@Valid @RequestBody CrearOrdenRequest request) {
        return ResponseEntity.ok(ordenService.crearOrden(request));
    }

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        return ResponseEntity.ok(ordenService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.buscarPorId(id));
    }

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Orden>> listarPorPerfil(@PathVariable Long perfilId) {
        return ResponseEntity.ok(ordenService.listarPorPerfil(perfilId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Orden> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado
    ) {
        return ResponseEntity.ok(ordenService.actualizarEstado(id, estado));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Orden>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(ordenService.listarPorEstado(estado));
    }
}