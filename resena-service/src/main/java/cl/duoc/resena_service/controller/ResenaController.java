package cl.duoc.resena_service.controller;

import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(resenaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ResenaDTO resena = resenaService.findById(id);
        return ResponseEntity.ok(resena);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Resena resena) {
        ResenaDTO resenaNueva = resenaService.save(resena);
        return new ResponseEntity<>(resenaNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Resena resena) {
        ResenaDTO resenaActualizada = resenaService.update(id, resena);
        return ResponseEntity.ok(resenaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        resenaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> buscarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(resenaService.buscarPorProducto(productoId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(resenaService.buscarPorCliente(clienteId));
    }

    @GetMapping("/calificacion/{calificacion}")
    public ResponseEntity<?> buscarPorCalificacion(@PathVariable Integer calificacion) {
        return ResponseEntity.ok(resenaService.buscarPorCalificacion(calificacion));
    }

    @GetMapping("/producto/{productoId}/promedio")
    public ResponseEntity<?> promedioPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(resenaService.promedioPorProducto(productoId));
    }
}