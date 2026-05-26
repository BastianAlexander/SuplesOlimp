package cl.duoc.carrito_service.service;

import cl.duoc.carrito_service.clients.ClienteFeign;
import cl.duoc.carrito_service.dto.CarritoDTO;
import cl.duoc.carrito_service.dto.CarritoItemDTO;
import cl.duoc.carrito_service.dto.ClienteDTO;
import cl.duoc.carrito_service.exception.CarritoNoExiste;
import cl.duoc.carrito_service.mapper.CarritoMapper;
import cl.duoc.carrito_service.model.Carrito;
import cl.duoc.carrito_service.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoMapper carritoMapper;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private CarritoItemService carritoItemService;

    public List<CarritoDTO> findAll() {
        List<Carrito> carritos = carritoRepository.findAll();

        return carritos.stream()
                .map(carrito -> {
                    ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(carrito.getClienteId());
                    List<CarritoItemDTO> items = carritoItemService.buscarItemsPorCarrito(carrito.getId());
                    return carritoMapper.toDTO(carrito, clienteDTO, items);
                })
                .collect(Collectors.toList());
    }

    public CarritoDTO findById(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);

        if (carrito == null) {
            throw new CarritoNoExiste("No existe carrito con ID: " + id);
        }

        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(carrito.getClienteId());
        List<CarritoItemDTO> items = carritoItemService.buscarItemsPorCarrito(carrito.getId());

        return carritoMapper.toDTO(carrito, clienteDTO, items);
    }

    public CarritoDTO save(Carrito carrito) {
        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(carrito.getClienteId());

        Carrito carritoGuardado = carritoRepository.save(carrito);

        List<CarritoItemDTO> items = carritoItemService.buscarItemsPorCarrito(carritoGuardado.getId());

        return carritoMapper.toDTO(carritoGuardado, clienteDTO, items);
    }

    public CarritoDTO update(Long id, Carrito carrito) {
        Carrito carritoActualizar = carritoRepository.findById(id).orElse(null);

        if (carritoActualizar == null) {
            throw new CarritoNoExiste("No existe carrito con ID: " + id);
        }

        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(carrito.getClienteId());

        carritoActualizar.setClienteId(carrito.getClienteId());

        Carrito carritoActualizado = carritoRepository.save(carritoActualizar);

        List<CarritoItemDTO> items = carritoItemService.buscarItemsPorCarrito(carritoActualizado.getId());

        return carritoMapper.toDTO(carritoActualizado, clienteDTO, items);
    }

    public void delete(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);

        if (carrito == null) {
            throw new CarritoNoExiste("No existe carrito con ID: " + id);
        }

        carritoRepository.deleteById(id);
    }

    //Metodo extras aparte del crud
    public List<CarritoDTO> buscarPorCliente(Long clienteId) {
        List<Carrito> carritos = carritoRepository.findByClienteId(clienteId);

        return carritos.stream()
                .map(carrito -> {
                    ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(carrito.getClienteId());
                    List<CarritoItemDTO> items = carritoItemService.buscarItemsPorCarrito(carrito.getId());
                    return carritoMapper.toDTO(carrito, clienteDTO, items);
                })
                .collect(Collectors.toList());
    }
}