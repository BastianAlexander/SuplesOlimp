package cl.duoc.carrito_service.controller;

import cl.duoc.carrito_service.dto.CarritoItemDTO;
import cl.duoc.carrito_service.model.CarritoItem;
import cl.duoc.carrito_service.service.CarritoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/carrito-items")
public class CarritoItemController {

    @Autowired
    private CarritoItemService carritoItemService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(carritoItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        CarritoItemDTO carritoItem = carritoItemService.findById(id);
        return ResponseEntity.ok(carritoItem);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody CarritoItem carritoItem) {
        CarritoItemDTO carritoItemNuevo = carritoItemService.save(carritoItem);
        return new ResponseEntity<>(carritoItemNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody CarritoItem carritoItem) {
        CarritoItemDTO carritoItemActualizado = carritoItemService.update(id, carritoItem);
        return ResponseEntity.ok(carritoItemActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        carritoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carrito/{carritoId}")
    public ResponseEntity<?> buscarItemsPorCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(carritoItemService.buscarItemsPorCarrito(carritoId));
    }

    @DeleteMapping("/carrito/{carritoId}/vaciar")
    public ResponseEntity<?> vaciarCarrito(@PathVariable Long carritoId) {
        carritoItemService.vaciarCarrito(carritoId);
        return ResponseEntity.noContent().build();
    }
}