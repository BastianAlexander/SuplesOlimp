package cl.duoc.pago_service.service;

import cl.duoc.pago_service.clients.OrdenFeign;
import cl.duoc.pago_service.dto.OrdenDTO;
import cl.duoc.pago_service.dto.Pagar;
import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.exception.PagoNoExiste;
import cl.duoc.pago_service.exception.PagoNoPuedeRealizarse;
import cl.duoc.pago_service.mapper.PagoMapper;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private OrdenFeign ordenFeign;

    public List<PagoDTO> findAll() {
        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagoDTO findById(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);

        if (pago == null) {
            throw new PagoNoExiste("No existe pago con ID: " + id);
        }

        return pagoMapper.toDTO(pago);
    }

    public void delete(Long id) {
        Pago pago = pagoRepository.findById(id).orElse(null);

        if (pago == null) {
            throw new PagoNoExiste("No existe pago con ID: " + id);
        }

        pagoRepository.deleteById(id);
    }

    public PagoDTO pagarOrden(Long ordenId, Pagar request) {
        OrdenDTO ordenDTO = ordenFeign.buscarOrdenPorId(ordenId);

        if (ordenDTO == null) {
            throw new PagoNoPuedeRealizarse("No se encontró la orden con ID: " + ordenId);
        }

        if ("PAGADA".equalsIgnoreCase(ordenDTO.getEstado())) {
            throw new PagoNoPuedeRealizarse("La orden ya está pagada");
        }

        if (!request.getMonto().equals(ordenDTO.getTotal())) {
            throw new PagoNoPuedeRealizarse(
                    "El monto ingresado no coincide con el total de la orden. Total esperado: " + ordenDTO.getTotal()
            );
        }

        Pago pago = new Pago();
        pago.setOrdenId(ordenDTO.getId());
        pago.setMonto(request.getMonto());
        pago.setMetodoPago(request.getMetodoPago());
        pago.setEstado("APROBADO");
        pago.setFechaPago(LocalDateTime.now());

        Pago pagoGuardado = pagoRepository.save(pago);

        ordenFeign.cambiarEstado(ordenDTO.getId(), "PAGADA");

        return pagoMapper.toDTO(pagoGuardado);
    }

    public List<PagoDTO> buscarPorOrden(Long ordenId) {
        return pagoRepository.findByOrdenId(ordenId)
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PagoDTO> buscarPorEstado(String estado) {
        return pagoRepository.findByEstadoIgnoreCase(estado)
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PagoDTO> buscarPorMetodoPago(String metodoPago) {
        return pagoRepository.findByMetodoPagoIgnoreCase(metodoPago)
                .stream()
                .map(pagoMapper::toDTO)
                .collect(Collectors.toList());
    }
}