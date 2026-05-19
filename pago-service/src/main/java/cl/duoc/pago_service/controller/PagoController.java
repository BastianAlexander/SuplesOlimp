package cl.duoc.pago_service.controller;

import cl.duoc.pago_service.dto.CrearPagoRequest;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> crear(@Valid @RequestBody CrearPagoRequest request) {
        return ResponseEntity.ok(pagoService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<Pago> buscarPorOrdenId(@PathVariable Long ordenId) {
        return ResponseEntity.ok(pagoService.buscarPorOrdenId(ordenId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pago>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pagoService.listarPorEstado(estado));
    }

    @GetMapping("/metodo/{metodo}")
    public ResponseEntity<List<Pago>> listarPorMetodo(@PathVariable String metodo) {
        return ResponseEntity.ok(pagoService.listarPorMetodo(metodo));
    }
}