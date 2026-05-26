package cl.duoc.producto_service.controller;

import cl.duoc.producto_service.dto.ProductoDTO;
import cl.duoc.producto_service.model.Producto;
import cl.duoc.producto_service.service.CategoriaService;
import cl.duoc.producto_service.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ProductoDTO producto = productoService.findById(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoNuevo = productoService.save(productoDTO);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = productoService.update(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Endpoints Extrass
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<?> buscarPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(idCategoria));
    }

    @GetMapping("/precio/{precioMin}/{precioMax}")
    public ResponseEntity<?> buscarPorRangoPrecio(@PathVariable Integer precioMin, @PathVariable Integer precioMax) {
        return ResponseEntity.ok(productoService.buscarPorRangoPrecio(precioMin, precioMax));
    }
}