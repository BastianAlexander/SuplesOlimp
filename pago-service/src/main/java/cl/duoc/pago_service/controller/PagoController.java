package cl.duoc.pago_service.controller;

import cl.duoc.pago_service.dto.Pagar;
import cl.duoc.pago_service.dto.Pagar;
import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PagoDTO pago = pagoService.findById(id);
        return ResponseEntity.ok(pago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/pagar-orden/{ordenId}")
    public ResponseEntity<?> pagarOrden(@PathVariable Long ordenId, @Valid @RequestBody Pagar request) {
        PagoDTO pago = pagoService.pagarOrden(ordenId, request);
        return new ResponseEntity<>(pago, HttpStatus.CREATED);
    }



    //Endpoinst Extrassss
    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<?> buscarPorOrden(@PathVariable Long ordenId) {
        return ResponseEntity.ok(pagoService.buscarPorOrden(ordenId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pagoService.buscarPorEstado(estado));
    }

    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<?> buscarPorMetodoPago(@PathVariable String metodoPago) {
        return ResponseEntity.ok(pagoService.buscarPorMetodoPago(metodoPago));
    }
}