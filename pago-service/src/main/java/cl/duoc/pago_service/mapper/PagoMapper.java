package cl.duoc.pago_service.mapper;

import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.model.Pago;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();

        dto.setId(pago.getId());
        dto.setOrdenId(pago.getOrdenId());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstado(pago.getEstado());
        dto.setFechaPago(pago.getFechaPago());

        return dto;
    }
}