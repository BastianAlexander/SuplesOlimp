package cl.duoc.orden_service.service;

import cl.duoc.orden_service.client.CarritoClient;
import cl.duoc.orden_service.client.InventarioClient;
import cl.duoc.orden_service.client.PerfilClient;
import cl.duoc.orden_service.client.ProductoClient;
import cl.duoc.orden_service.dto.CarritoItemResponse;
import cl.duoc.orden_service.dto.CrearOrdenRequest;
import cl.duoc.orden_service.dto.DescontarStockRequest;
import cl.duoc.orden_service.dto.ProductoResponse;
import cl.duoc.orden_service.model.Orden;
import cl.duoc.orden_service.model.OrdenItem;
import cl.duoc.orden_service.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final PerfilClient perfilClient;
    private final CarritoClient carritoClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;

    public Orden crearOrden(CrearOrdenRequest request) {

        perfilClient.buscarPerfilPorId(request.getPerfilId());

        List<CarritoItemResponse> carritoItems = carritoClient.listarPorPerfil(request.getPerfilId());

        if (carritoItems.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<OrdenItem> ordenItems = new ArrayList<>();

        Orden orden = Orden.builder()
                .perfilId(request.getPerfilId())
                .estado("PENDIENTE")
                .fecha(LocalDateTime.now())
                .total(BigDecimal.ZERO)
                .items(ordenItems)
                .build();

        for (CarritoItemResponse itemCarrito : carritoItems) {

            ProductoResponse producto = productoClient.buscarProductoPorId(itemCarrito.getProductoId());

            BigDecimal subtotal = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(itemCarrito.getCantidad()));

            total = total.add(subtotal);

            inventarioClient.descontarStock(
                    itemCarrito.getProductoId(),
                    new DescontarStockRequest(itemCarrito.getCantidad())
            );

            OrdenItem ordenItem = OrdenItem.builder()
                    .orden(orden)
                    .productoId(itemCarrito.getProductoId())
                    .cantidad(itemCarrito.getCantidad())
                    .precio(producto.getPrecio())
                    .build();

            ordenItems.add(ordenItem);
        }

        orden.setTotal(total);

        Orden ordenGuardada = ordenRepository.save(orden);

        carritoClient.vaciarCarrito(request.getPerfilId());

        return ordenGuardada;
    }

    public List<Orden> listar() {
        return ordenRepository.findAll();
    }

    public Orden buscarPorId(Long id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    public List<Orden> listarPorPerfil(Long perfilId) {
        return ordenRepository.findByPerfilId(perfilId);
    }

    public Orden actualizarEstado(Long id, String estado) {
        Orden orden = buscarPorId(id);
        orden.setEstado(estado);
        return ordenRepository.save(orden);
    }

    public List<Orden> listarPorEstado(String estado) {
        return ordenRepository.findByEstadoIgnoreCase(estado);
    }
}