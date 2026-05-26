package cl.duoc.orden_service.controller;

import cl.duoc.orden_service.dto.OrdenDTO;
import cl.duoc.orden_service.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(ordenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        OrdenDTO orden = ordenService.findById(id);
        return ResponseEntity.ok(orden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/crear-desde-carrito/{carritoId}")
    public ResponseEntity<?> crearDesdeCarrito(@PathVariable Long carritoId) {
        OrdenDTO orden = ordenService.crearDesdeCarrito(carritoId);
        return new ResponseEntity<>(orden, HttpStatus.CREATED);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ordenService.buscarPorCliente(clienteId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(ordenService.buscarPorEstado(estado));
    }

    @PutMapping("/{id}/estado/{estado}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @PathVariable String estado) {
        OrdenDTO orden = ordenService.cambiarEstado(id, estado);
        return ResponseEntity.ok(orden);
    }
}