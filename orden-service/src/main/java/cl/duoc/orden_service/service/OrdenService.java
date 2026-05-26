package cl.duoc.orden_service.service;

import cl.duoc.orden_service.clients.CarritoFeign;
import cl.duoc.orden_service.clients.CarritoItemFeign;
import cl.duoc.orden_service.clients.InventarioFeign;
import cl.duoc.orden_service.dto.*;
import cl.duoc.orden_service.exception.OrdenNoExiste;
import cl.duoc.orden_service.exception.OrdenNoPuedeCrearse;
import cl.duoc.orden_service.exception.StockInsuficiente;
import cl.duoc.orden_service.mapper.OrdenItemMapper;
import cl.duoc.orden_service.mapper.OrdenMapper;
import cl.duoc.orden_service.model.Orden;
import cl.duoc.orden_service.model.OrdenItem;
import cl.duoc.orden_service.repository.OrdenItemRepository;
import cl.duoc.orden_service.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenItemRepository ordenItemRepository;

    @Autowired
    private OrdenMapper ordenMapper;

    @Autowired
    private OrdenItemMapper ordenItemMapper;

    @Autowired
    private CarritoFeign carritoFeign;

    @Autowired
    private CarritoItemFeign carritoItemFeign;

    @Autowired
    private InventarioFeign inventarioFeign;

    public List<OrdenDTO> findAll() {
        return ordenRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public OrdenDTO findById(Long id) {
        Orden orden = ordenRepository.findById(id).orElse(null);

        if (orden == null) {
            throw new OrdenNoExiste("No existe orden con ID: " + id);
        }

        return convertirADTO(orden);
    }

    public void delete(Long id) {
        Orden orden = ordenRepository.findById(id).orElse(null);

        if (orden == null) {
            throw new OrdenNoExiste("No existe orden con ID: " + id);
        }

        ordenRepository.deleteById(id);
    }

    @Transactional
    public OrdenDTO crearDesdeCarrito(Long carritoId) {

        if (ordenRepository.existsByCarritoId(carritoId)) {
            throw new OrdenNoPuedeCrearse("Este carrito ya generó una orden anteriormente");
        }

        CarritoDTO carritoDTO = carritoFeign.buscarCarritoPorId(carritoId);

        if (carritoDTO == null) {
            throw new OrdenNoPuedeCrearse("No se pudo encontrar el carrito con ID: " + carritoId);
        }

        if (carritoDTO.getItems() == null || carritoDTO.getItems().isEmpty()) {
            throw new OrdenNoPuedeCrearse("No se puede crear una orden con un carrito vacío");
        }

        Integer total = 0;

        for (CarritoItemDTO item : carritoDTO.getItems()) {
            ProductoDTO producto = item.getProducto();

            InventarioDTO inventario = inventarioFeign.buscarPorProductoId(producto.getId());

            if (inventario.getStock() < item.getCantidad()) {
                throw new StockInsuficiente("No hay stock suficiente para el producto: " + producto.getNombre());
            }

            Integer subtotal = producto.getPrecio() * item.getCantidad();
            total += subtotal;
        }

        Orden orden = new Orden();
        orden.setClienteId(carritoDTO.getCliente().getId());
        orden.setCarritoId(carritoDTO.getId());
        orden.setTotal(total);
        orden.setEstado("CREADA");
        orden.setFechaCreacion(LocalDateTime.now());

        Orden ordenGuardada = ordenRepository.save(orden);

        for (CarritoItemDTO item : carritoDTO.getItems()) {
            ProductoDTO producto = item.getProducto();

            OrdenItem ordenItem = new OrdenItem();
            ordenItem.setOrdenId(ordenGuardada.getId());
            ordenItem.setProductoId(producto.getId());
            ordenItem.setCantidad(item.getCantidad());
            ordenItem.setPrecioUnitario(producto.getPrecio());
            ordenItem.setSubtotal(producto.getPrecio() * item.getCantidad());

            ordenItemRepository.save(ordenItem);

            InventarioDTO inventario = inventarioFeign.buscarPorProductoId(producto.getId());
            inventarioFeign.disminuirStock(inventario.getId(), item.getCantidad());
        }

        carritoItemFeign.vaciarCarrito(carritoDTO.getId());

        return convertirADTO(ordenGuardada);
    }

    public List<OrdenDTO> buscarPorCliente(Long clienteId) {
        return ordenRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<OrdenDTO> buscarPorEstado(String estado) {
        return ordenRepository.findByEstadoIgnoreCase(estado)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public OrdenDTO cambiarEstado(Long id, String estado) {
        Orden orden = ordenRepository.findById(id).orElse(null);

        if (orden == null) {
            throw new OrdenNoExiste("No existe orden con ID: " + id);
        }

        orden.setEstado(estado);

        Orden ordenActualizada = ordenRepository.save(orden);

        return convertirADTO(ordenActualizada);
    }

    private OrdenDTO convertirADTO(Orden orden) {
        CarritoDTO carritoDTO = carritoFeign.buscarCarritoPorId(orden.getCarritoId());

        List<OrdenItem> items = ordenItemRepository.findByOrdenId(orden.getId());

        List<OrdenItemDTO> itemsDTO = items.stream()
                .map(item -> {
                    InventarioDTO inventarioDTO = inventarioFeign.buscarPorProductoId(item.getProductoId());
                    ProductoDTO productoDTO = inventarioDTO.getProducto();

                    return ordenItemMapper.toDTO(item, productoDTO);
                })
                .collect(Collectors.toList());

        return ordenMapper.toDTO(orden, carritoDTO.getCliente(), itemsDTO);
    }
}