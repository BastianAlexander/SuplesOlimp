package cl.duoc.carrito_service.controller;

import cl.duoc.carrito_service.dto.ActualizarCantidadRequest;
import cl.duoc.carrito_service.dto.AgregarCarritoRequest;
import cl.duoc.carrito_service.model.CarritoItem;
import cl.duoc.carrito_service.service.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoItem> agregar(@Valid @RequestBody AgregarCarritoRequest request) {
        return ResponseEntity.ok(carritoService.agregar(request));
    }

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<CarritoItem>> listarPorPerfil(@PathVariable Long perfilId) {
        return ResponseEntity.ok(carritoService.listarPorPerfil(perfilId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoItem> actualizarCantidad(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarCantidadRequest request
    ) {
        return ResponseEntity.ok(carritoService.actualizarCantidad(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        carritoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado del carrito correctamente");
    }

    @DeleteMapping("/perfil/{perfilId}")
    public ResponseEntity<String> vaciarCarrito(@PathVariable Long perfilId) {
        carritoService.vaciarCarrito(perfilId);
        return ResponseEntity.ok("Carrito vaciado correctamente");
    }

    @GetMapping("/perfil/{perfilId}/cantidad-items")
    public ResponseEntity<Integer> contarItemsPorPerfil(@PathVariable Long perfilId) {
        return ResponseEntity.ok(carritoService.contarItemsPorPerfil(perfilId));
    }
}