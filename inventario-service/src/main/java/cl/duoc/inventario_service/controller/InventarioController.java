package cl.duoc.inventario_service.controller;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<?> listarInventarios() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        InventarioDTO inventarioDTO = inventarioService.findById(id);
        if (inventarioDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventarioDTO);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Inventario inventario) {
        InventarioDTO inventarioNuevo = inventarioService.save(inventario);
        return new ResponseEntity<>(inventarioNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        InventarioDTO inventarioActualizado = inventarioService.update(id, inventario);
        if (inventarioActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Endpoints extras aparte del crud
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> buscarPorProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @PutMapping("/{id}/aumentar/{cantidad}")
    public ResponseEntity<?> aumentarStock(@PathVariable Long id, @PathVariable Integer cantidad) {
        return ResponseEntity.ok(inventarioService.aumentarStock(id, cantidad));
    }

    @PutMapping("/{id}/disminuir/{cantidad}")
    public ResponseEntity<?> disminuirStock(@PathVariable Long id, @PathVariable Integer cantidad) {
        return ResponseEntity.ok(inventarioService.disminuirStock(id, cantidad));
    }
}