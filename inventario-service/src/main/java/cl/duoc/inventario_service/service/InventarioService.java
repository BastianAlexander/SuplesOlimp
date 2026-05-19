package cl.duoc.inventario_service.service;

import cl.duoc.inventario_service.client.ProductoClient;
import cl.duoc.inventario_service.dto.ActualizarStockRequest;
import cl.duoc.inventario_service.dto.CrearInventarioRequest;
import cl.duoc.inventario_service.dto.DescontarStockRequest;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoClient productoClient;

    public Inventario crear(CrearInventarioRequest request) {

        productoClient.buscarProductoPorId(request.getProductoId());

        if (inventarioRepository.existsByProductoId(request.getProductoId())) {
            throw new RuntimeException("Este producto ya tiene inventario");
        }

        Inventario inventario = Inventario.builder()
                .productoId(request.getProductoId())
                .stock(request.getStock())
                .activo(true)
                .build();

        return inventarioRepository.save(inventario);
    }

    public List<Inventario> listar() {
        return inventarioRepository.findByActivoTrue();
    }

    public Inventario buscarPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    public Inventario buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para este producto"));
    }

    public Inventario actualizarStock(Long productoId, ActualizarStockRequest request) {

        Inventario inventario = buscarPorProductoId(productoId);

        inventario.setStock(request.getStock());

        return inventarioRepository.save(inventario);
    }

    public Inventario descontarStock(Long productoId, DescontarStockRequest request) {

        Inventario inventario = buscarPorProductoId(productoId);

        if (inventario.getStock() < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setStock(inventario.getStock() - request.getCantidad());

        return inventarioRepository.save(inventario);
    }

    public void eliminar(Long id) {

        Inventario inventario = buscarPorId(id);

        inventario.setActivo(false);

        inventarioRepository.save(inventario);
    }

    public List<Inventario> listarBajoStock(Integer minimo) {
        return inventarioRepository.findByStockLessThanEqualAndActivoTrue(minimo);
    }
}