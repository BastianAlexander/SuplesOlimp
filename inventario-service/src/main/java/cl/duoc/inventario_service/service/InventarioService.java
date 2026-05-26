package cl.duoc.inventario_service.service;

import cl.duoc.inventario_service.clients.ProductoFeign;
import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.dto.ProductoDTO;
import cl.duoc.inventario_service.exception.InventarioNoExiste;
import cl.duoc.inventario_service.mapper.InventarioMapper;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private InventarioMapper inventarioMapper;

    @Autowired
    private ProductoFeign productoFeign;

    public List<InventarioDTO> findAll() {
        List<Inventario> inventarios = inventarioRepository.findAll();

        return inventarios.stream()
                .map(inventario -> {
                    ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventario.getProductoId());
                    return inventarioMapper.toDTO(inventario, productoDTO);
                })
                .collect(Collectors.toList());
    }

    public InventarioDTO findById(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);

        if (inventario == null) {
            throw new InventarioNoExiste("No existe inventario con ID: " + id);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventario.getProductoId());

        return inventarioMapper.toDTO(inventario, productoDTO);
    }

    public InventarioDTO save(Inventario inventario) {
        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventario.getProductoId());

        Inventario inventarioGuardado = inventarioRepository.save(inventario);

        return inventarioMapper.toDTO(inventarioGuardado, productoDTO);
    }

    public InventarioDTO update(Long id, Inventario inventario) {
        Inventario inventarioActualizar = inventarioRepository.findById(id).orElse(null);

        if (inventarioActualizar == null) {
            throw new InventarioNoExiste("No existe inventario con ID: " + id);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventario.getProductoId());

        inventarioActualizar.setProductoId(inventario.getProductoId());
        inventarioActualizar.setStock(inventario.getStock());

        Inventario inventarioActualizado = inventarioRepository.save(inventarioActualizar);

        return inventarioMapper.toDTO(inventarioActualizado, productoDTO);
    }

    public void delete(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);

        if (inventario == null) {
            throw new InventarioNoExiste("No existe inventario con ID: " + id);
        }

        inventarioRepository.deleteById(id);
    }


    //Metodos extrass aparte del crud

    public InventarioDTO buscarPorProductoId(Long productoId) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId);

        if (inventario == null) {
            throw new InventarioNoExiste("No existe inventario para el producto con ID: " + productoId);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventario.getProductoId());

        return inventarioMapper.toDTO(inventario, productoDTO);
    }

    public InventarioDTO aumentarStock(Long id, Integer cantidad) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);

        if (inventario == null) {
            throw new InventarioNoExiste("No existe inventario con ID: " + id);
        }

        inventario.setStock(inventario.getStock() + cantidad);

        Inventario inventarioActualizado = inventarioRepository.save(inventario);

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventarioActualizado.getProductoId());

        return inventarioMapper.toDTO(inventarioActualizado, productoDTO);
    }

    public InventarioDTO disminuirStock(Long id, Integer cantidad) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);

        if (inventario == null) {
            throw new InventarioNoExiste("No existe inventario con ID: " + id);
        }

        if (inventario.getStock() - cantidad < 0) {
            throw new RuntimeException("No hay stock suficiente");
        }

        inventario.setStock(inventario.getStock() - cantidad);

        Inventario inventarioActualizado = inventarioRepository.save(inventario);

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(inventarioActualizado.getProductoId());

        return inventarioMapper.toDTO(inventarioActualizado, productoDTO);
    }
}