package cl.duoc.producto_service.controller;

import cl.duoc.producto_service.model.Categoria;
import cl.duoc.producto_service.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaNueva = categoriaService.save(categoria);
        return new ResponseEntity<>(categoriaNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaActualizada = categoriaService.update(id, categoria);
        if (categoriaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}