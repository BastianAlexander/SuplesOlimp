package cl.duoc.inventario_service.controller;

import cl.duoc.inventario_service.dto.ActualizarStockRequest;
import cl.duoc.inventario_service.dto.CrearInventarioRequest;
import cl.duoc.inventario_service.dto.DescontarStockRequest;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody CrearInventarioRequest request) {
        return ResponseEntity.ok(inventarioService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @PutMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> actualizarStock(
            @PathVariable Long productoId,
            @Valid @RequestBody ActualizarStockRequest request
    ) {
        return ResponseEntity.ok(inventarioService.actualizarStock(productoId, request));
    }

    @PutMapping("/producto/{productoId}/descontar")
    public ResponseEntity<Inventario> descontarStock(
            @PathVariable Long productoId,
            @Valid @RequestBody DescontarStockRequest request
    ) {
        return ResponseEntity.ok(inventarioService.descontarStock(productoId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.ok("Inventario eliminado correctamente");
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<Inventario>> listarBajoStock(@RequestParam Integer minimo) {
        return ResponseEntity.ok(inventarioService.listarBajoStock(minimo));
    }
}