package cl.duoc.producto_service.service;

import cl.duoc.producto_service.dto.ProductoDTO;
import cl.duoc.producto_service.exception.ProductoNoExiste;
import cl.duoc.producto_service.mapper.ProductoMapper;
import cl.duoc.producto_service.model.Categoria;
import cl.duoc.producto_service.model.Producto;
import cl.duoc.producto_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProductoDTO> findAll() {
        return productoMapper.toDTOList(productoRepository.findAll());
    }

    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto == null) {
            throw new ProductoNoExiste("No existe el producto con ID: " + id);
        }

        return productoMapper.toDTO(producto);
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        Categoria categoria = categoriaService.findById(productoDTO.getIdCategoria());

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCategoria(categoria);

        Producto productoGuardado = productoRepository.save(producto);

        return productoMapper.toDTO(productoGuardado);
    }

    public ProductoDTO update(Long id, ProductoDTO productoDTO) {
        Producto productoActualizar = productoRepository.findById(id).orElse(null);

        if (productoActualizar == null) {
            throw new ProductoNoExiste("No existe el producto con ID: " + id);
        }

        Categoria categoria = categoriaService.findById(productoDTO.getIdCategoria());

        productoActualizar.setNombre(productoDTO.getNombre());
        productoActualizar.setPrecio(productoDTO.getPrecio());
        productoActualizar.setDescripcion(productoDTO.getDescripcion());
        productoActualizar.setCategoria(categoria);

        Producto productoActualizado = productoRepository.save(productoActualizar);

        return productoMapper.toDTO(productoActualizado);
    }


    public void delete(Long id) {
        productoRepository.deleteById(id);
    }


    //Endpoints Extrasss

    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return productoMapper.toDTOList(productoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    public List<ProductoDTO> buscarPorCategoria(Long idCategoria) {
        return productoMapper.toDTOList(productoRepository.findByCategoria_Id(idCategoria));
    }

    public List<ProductoDTO> buscarPorRangoPrecio(Integer precioMin, Integer precioMax) {
        return productoMapper.toDTOList(productoRepository.findByPrecioBetween(precioMin, precioMax));
    }
}