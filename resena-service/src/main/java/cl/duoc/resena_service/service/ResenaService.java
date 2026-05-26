package cl.duoc.resena_service.service;

import cl.duoc.resena_service.clients.ClienteFeign;
import cl.duoc.resena_service.clients.ProductoFeign;
import cl.duoc.resena_service.dto.ClienteDTO;
import cl.duoc.resena_service.dto.ProductoDTO;
import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.exception.ResenaNoExiste;
import cl.duoc.resena_service.exception.ResenaNoPuedeCrearse;
import cl.duoc.resena_service.mapper.ResenaMapper;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ResenaMapper resenaMapper;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    public List<ResenaDTO> findAll() {
        return resenaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ResenaDTO findById(Long id) {
        Resena resena = resenaRepository.findById(id).orElse(null);

        if (resena == null) {
            throw new ResenaNoExiste("No existe reseña con ID: " + id);
        }

        return convertirADTO(resena);
    }

    public ResenaDTO save(Resena resena) {
        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(resena.getProductoId());
        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(resena.getClienteId());

        if (productoDTO == null) {
            throw new ResenaNoPuedeCrearse("No existe producto con ID: " + resena.getProductoId());
        }

        if (clienteDTO == null) {
            throw new ResenaNoPuedeCrearse("No existe cliente con ID: " + resena.getClienteId());
        }

        resena.setFechaCreacion(LocalDateTime.now());

        Resena resenaGuardada = resenaRepository.save(resena);

        return resenaMapper.toDTO(resenaGuardada, productoDTO, clienteDTO);
    }

    public ResenaDTO update(Long id, Resena resena) {
        Resena resenaActualizar = resenaRepository.findById(id).orElse(null);

        if (resenaActualizar == null) {
            throw new ResenaNoExiste("No existe reseña con ID: " + id);
        }

        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(resena.getProductoId());
        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(resena.getClienteId());

        if (productoDTO == null) {
            throw new ResenaNoPuedeCrearse("No existe producto con ID: " + resena.getProductoId());
        }

        if (clienteDTO == null) {
            throw new ResenaNoPuedeCrearse("No existe cliente con ID: " + resena.getClienteId());
        }

        resenaActualizar.setProductoId(resena.getProductoId());
        resenaActualizar.setClienteId(resena.getClienteId());
        resenaActualizar.setComentario(resena.getComentario());
        resenaActualizar.setCalificacion(resena.getCalificacion());

        Resena resenaActualizada = resenaRepository.save(resenaActualizar);

        return resenaMapper.toDTO(resenaActualizada, productoDTO, clienteDTO);
    }

    public void delete(Long id) {
        Resena resena = resenaRepository.findById(id).orElse(null);

        if (resena == null) {
            throw new ResenaNoExiste("No existe reseña con ID: " + id);
        }

        resenaRepository.deleteById(id);
    }

    public List<ResenaDTO> buscarPorProducto(Long productoId) {
        return resenaRepository.findByProductoId(productoId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<ResenaDTO> buscarPorCliente(Long clienteId) {
        return resenaRepository.findByClienteId(clienteId)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<ResenaDTO> buscarPorCalificacion(Integer calificacion) {
        return resenaRepository.findByCalificacion(calificacion)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Double promedioPorProducto(Long productoId) {
        List<Resena> resenas = resenaRepository.findByProductoId(productoId);

        if (resenas.isEmpty()) {
            return 0.0;
        }

        return resenas.stream()
                .mapToInt(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    }

    private ResenaDTO convertirADTO(Resena resena) {
        ProductoDTO productoDTO = productoFeign.buscarProductoPorId(resena.getProductoId());
        ClienteDTO clienteDTO = clienteFeign.buscarClientePorId(resena.getClienteId());

        return resenaMapper.toDTO(resena, productoDTO, clienteDTO);
    }
}