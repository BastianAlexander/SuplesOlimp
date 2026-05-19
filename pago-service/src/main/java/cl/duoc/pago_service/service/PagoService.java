package cl.duoc.pago_service.service;

import cl.duoc.pago_service.client.OrdenClient;
import cl.duoc.pago_service.dto.CrearPagoRequest;
import cl.duoc.pago_service.dto.OrdenResponse;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final OrdenClient ordenClient;

    public Pago crear(CrearPagoRequest request) {

        OrdenResponse orden = ordenClient.buscarOrdenPorId(request.getOrdenId());

        if (pagoRepository.existsByOrdenId(request.getOrdenId())) {
            throw new RuntimeException("Esta orden ya tiene un pago registrado");
        }

        if (request.getMonto().compareTo(orden.getTotal()) != 0) {
            throw new RuntimeException("El monto del pago no coincide con el total de la orden");
        }

        Pago pago = Pago.builder()
                .ordenId(request.getOrdenId())
                .monto(request.getMonto())
                .metodo(request.getMetodo())
                .estado("PAGADO")
                .build();

        Pago pagoGuardado = pagoRepository.save(pago);

        ordenClient.actualizarEstado(request.getOrdenId(), "PAGADA");

        return pagoGuardado;
    }

    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public Pago buscarPorOrdenId(Long ordenId) {
        return pagoRepository.findByOrdenId(ordenId)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado para esta orden"));
    }

    public List<Pago> listarPorEstado(String estado) {
        return pagoRepository.findByEstadoIgnoreCase(estado);
    }

    public List<Pago> listarPorMetodo(String metodo) {
        return pagoRepository.findByMetodoIgnoreCase(metodo);
    }
}