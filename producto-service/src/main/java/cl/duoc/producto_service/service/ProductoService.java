package cl.duoc.producto_service.service;

import cl.duoc.producto_service.dto.ActualizarProductoRequest;
import cl.duoc.producto_service.dto.CrearProductoRequest;
import cl.duoc.producto_service.model.Producto;
import cl.duoc.producto_service.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto crear(CrearProductoRequest request) {

        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .marca(request.getMarca())
                .precio(request.getPrecio())
                .activo(true)
                .build();

        return productoRepository.save(producto);
    }

    public List<Producto> listar() {
        return productoRepository.findByActivoTrue();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCaseAndActivoTrue(nombre);
    }

    public List<Producto> buscarPorMarca(String marca) {
        return productoRepository.findByMarcaContainingIgnoreCaseAndActivoTrue(marca);
    }

    public Producto actualizar(Long id, ActualizarProductoRequest request) {

        Producto producto = buscarPorId(id);

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setMarca(request.getMarca());
        producto.setPrecio(request.getPrecio());

        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {

        Producto producto = buscarPorId(id);

        producto.setActivo(false);

        productoRepository.save(producto);
    }

    public List<Producto> buscarPorRangoPrecio(BigDecimal min, BigDecimal max) {
        return productoRepository.findByPrecioBetweenAndActivoTrue(min, max);
    }
}