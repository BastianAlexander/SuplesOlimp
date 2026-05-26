package cl.duoc.carrito_service.controller;

import cl.duoc.carrito_service.dto.CarritoDTO;
import cl.duoc.carrito_service.model.Carrito;
import cl.duoc.carrito_service.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(carritoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        CarritoDTO carrito = carritoService.findById(id);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Carrito carrito) {
        CarritoDTO carritoNuevo = carritoService.save(carrito);
        return new ResponseEntity<>(carritoNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Carrito carrito) {
        CarritoDTO carritoActualizado = carritoService.update(id, carrito);
        return ResponseEntity.ok(carritoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        carritoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    //Endpoints extras aparte del crud
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(carritoService.buscarPorCliente(clienteId));
    }
}