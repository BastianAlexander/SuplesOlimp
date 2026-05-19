package cl.duoc.resena_service.controller;

import cl.duoc.resena_service.dto.ActualizarResenaRequest;
import cl.duoc.resena_service.dto.CrearResenaRequest;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.service.ResenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping
    public ResponseEntity<Resena> crear(@Valid @RequestBody CrearResenaRequest request) {
        return ResponseEntity.ok(resenaService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Resena>> listar() {
        return ResponseEntity.ok(resenaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.buscarPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resena>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(resenaService.listarPorProducto(productoId));
    }

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Resena>> listarPorPerfil(@PathVariable Long perfilId) {
        return ResponseEntity.ok(resenaService.listarPorPerfil(perfilId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarResenaRequest request
    ) {
        return ResponseEntity.ok(resenaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.ok("Reseña eliminada correctamente");
    }

    @GetMapping("/producto/{productoId}/promedio")
    public ResponseEntity<Double> obtenerPromedioPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(resenaService.obtenerPromedioPorProducto(productoId));
    }
}