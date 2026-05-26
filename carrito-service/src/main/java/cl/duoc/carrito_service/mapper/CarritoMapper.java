package cl.duoc.carrito_service.mapper;

import cl.duoc.carrito_service.dto.CarritoDTO;
import cl.duoc.carrito_service.dto.CarritoItemDTO;
import cl.duoc.carrito_service.dto.ClienteDTO;
import cl.duoc.carrito_service.model.Carrito;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarritoMapper {

    public CarritoDTO toDTO(Carrito carrito, ClienteDTO clienteDTO, List<CarritoItemDTO> items) {
        if (carrito == null) {
            return null;
        }

        CarritoDTO dto = new CarritoDTO();

        dto.setId(carrito.getId());
        dto.setCliente(clienteDTO);
        dto.setItems(items);

        return dto;
    }

    public Carrito toEntity(CarritoDTO dto) {
        if (dto == null) {
            return null;
        }

        Carrito carrito = new Carrito();

        carrito.setId(dto.getId());

        if (dto.getCliente() != null) {
            carrito.setClienteId(dto.getCliente().getId());
        }

        return carrito;
    }
}