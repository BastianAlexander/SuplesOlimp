package cl.duoc.carrito_service.service;

import cl.duoc.carrito_service.clients.ProductoFeign;
import cl.duoc.carrito_service.dto.CarritoItemDTO;
import cl.duoc.carrito_service.dto.ProductoDTO;
import cl.duoc.carrito_service.exception.CarritoItemNoExiste;
import cl.duoc.carrito_service.mapper.CarritoItemMapper;
import cl.duoc.carrito_service.model.CarritoItem;
import cl.duoc.carrito_service.repository.CarritoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoItemService {

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private CarritoItemMapper carritoItemMapper;

    @Autowired
    private ProductoFeign productoFeign;

    public List<CarritoItemDTO> findAll() {
        List<CarritoItem> items = carritoItemRepository.findAll();

        return items.stream()
                .map(item -> {
                    ProductoDTO productoDTO = productoFeign.buscarProductoPorId(item.getProductoId());
                    return carritoItemMapper.toDTO(item, productoDTO);
                })
                .collect(Collectors.toList());
    }

    public CarritoItemDTO findById(Long id) {
        CarritoItem carritoItem = carritoItemRepository.findById(id).orElse(null);

        if (carritoItem == null) {
            throw new CarritoItemNoExiste("No existe item del carrito con ID: " + id);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(carritoItem.getProductoId());

        return carritoItemMapper.toDTO(carritoItem, productoDTO);
    }

    public CarritoItemDTO save(CarritoItem carritoItem) {
        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(carritoItem.getProductoId());

        CarritoItem carritoItemGuardado = carritoItemRepository.save(carritoItem);

        return carritoItemMapper.toDTO(carritoItemGuardado, productoDTO);
    }

    public CarritoItemDTO update(Long id, CarritoItem carritoItem) {
        CarritoItem carritoItemActualizar = carritoItemRepository.findById(id).orElse(null);

        if (carritoItemActualizar == null) {
            throw new CarritoItemNoExiste("No existe item del carrito con ID: " + id);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(carritoItem.getProductoId());

        carritoItemActualizar.setCarritoId(carritoItem.getCarritoId());
        carritoItemActualizar.setProductoId(carritoItem.getProductoId());
        carritoItemActualizar.setCantidad(carritoItem.getCantidad());

        CarritoItem carritoItemActualizado = carritoItemRepository.save(carritoItemActualizar);

        return carritoItemMapper.toDTO(carritoItemActualizado, productoDTO);
    }

    public void delete(Long id) {
        CarritoItem carritoItem = carritoItemRepository.findById(id).orElse(null);

        if (carritoItem == null) {
            throw new CarritoItemNoExiste("No existe item del carrito con ID: " + id);
        }

        carritoItemRepository.deleteById(id);
    }

    //Metodo extras aparte del crud
    public List<CarritoItemDTO> buscarItemsPorCarrito(Long carritoId) {
        List<CarritoItem> items = carritoItemRepository.findByCarritoId(carritoId);

        return items.stream()
                .map(item -> {
                    ProductoDTO productoDTO = productoFeign.buscarProductoPorId(item.getProductoId());
                    return carritoItemMapper.toDTO(item, productoDTO);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void vaciarCarrito(Long carritoId) {
        carritoItemRepository.eliminarItemsPorCarritoId(carritoId);
    }
}