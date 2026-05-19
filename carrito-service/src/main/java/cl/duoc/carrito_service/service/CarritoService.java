package cl.duoc.carrito_service.service;

import cl.duoc.carrito_service.client.InventarioClient;
import cl.duoc.carrito_service.client.PerfilClient;
import cl.duoc.carrito_service.client.ProductoClient;
import cl.duoc.carrito_service.dto.ActualizarCantidadRequest;
import cl.duoc.carrito_service.dto.AgregarCarritoRequest;
import cl.duoc.carrito_service.model.CarritoItem;
import cl.duoc.carrito_service.repository.CarritoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoItemRepository carritoItemRepository;
    private final PerfilClient perfilClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;

    public CarritoItem agregar(AgregarCarritoRequest request) {

        perfilClient.buscarPerfilPorId(request.getPerfilId());
        productoClient.buscarProductoPorId(request.getProductoId());

        Map<String, Object> inventario = inventarioClient.buscarInventarioPorProductoId(request.getProductoId());
        Integer stock = (Integer) inventario.get("stock");

        if (stock < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para agregar al carrito");
        }

        var itemExistente = carritoItemRepository
                .findByPerfilIdAndProductoIdAndActivoTrue(request.getPerfilId(), request.getProductoId());

        if (itemExistente.isPresent()) {
            CarritoItem item = itemExistente.get();
            int nuevaCantidad = item.getCantidad() + request.getCantidad();

            if (stock < nuevaCantidad) {
                throw new RuntimeException("Stock insuficiente para esta cantidad total");
            }

            item.setCantidad(nuevaCantidad);
            return carritoItemRepository.save(item);
        }

        CarritoItem item = CarritoItem.builder()
                .perfilId(request.getPerfilId())
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .activo(true)
                .build();

        return carritoItemRepository.save(item);
    }

    public List<CarritoItem> listarPorPerfil(Long perfilId) {
        perfilClient.buscarPerfilPorId(perfilId);
        return carritoItemRepository.findByPerfilIdAndActivoTrue(perfilId);
    }

    public CarritoItem actualizarCantidad(Long id, ActualizarCantidadRequest request) {

        CarritoItem item = carritoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item del carrito no encontrado"));

        Map<String, Object> inventario = inventarioClient.buscarInventarioPorProductoId(item.getProductoId());
        Integer stock = (Integer) inventario.get("stock");

        if (stock < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para actualizar cantidad");
        }

        item.setCantidad(request.getCantidad());

        return carritoItemRepository.save(item);
    }

    public void eliminar(Long id) {

        CarritoItem item = carritoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item del carrito no encontrado"));

        item.setActivo(false);

        carritoItemRepository.save(item);
    }

    public void vaciarCarrito(Long perfilId) {

        List<CarritoItem> items = carritoItemRepository.findByPerfilIdAndActivoTrue(perfilId);

        for (CarritoItem item : items) {
            item.setActivo(false);
        }

        carritoItemRepository.saveAll(items);
    }

    public int contarItemsPorPerfil(Long perfilId) {
        perfilClient.buscarPerfilPorId(perfilId);
        return carritoItemRepository.countByPerfilIdAndActivoTrue(perfilId);
    }
}