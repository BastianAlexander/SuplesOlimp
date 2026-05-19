package cl.duoc.resena_service.service;

import cl.duoc.resena_service.client.PerfilClient;
import cl.duoc.resena_service.client.ProductoClient;
import cl.duoc.resena_service.dto.ActualizarResenaRequest;
import cl.duoc.resena_service.dto.CrearResenaRequest;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final PerfilClient perfilClient;
    private final ProductoClient productoClient;

    public Resena crear(CrearResenaRequest request) {

        perfilClient.buscarPerfilPorId(request.getPerfilId());
        productoClient.buscarProductoPorId(request.getProductoId());

        Resena resena = Resena.builder()
                .perfilId(request.getPerfilId())
                .productoId(request.getProductoId())
                .puntuacion(request.getPuntuacion())
                .comentario(request.getComentario())
                .fecha(LocalDateTime.now())
                .activo(true)
                .build();

        return resenaRepository.save(resena);
    }

    public List<Resena> listar() {
        return resenaRepository.findByActivoTrue();
    }

    public Resena buscarPorId(Long id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public List<Resena> listarPorProducto(Long productoId) {
        productoClient.buscarProductoPorId(productoId);
        return resenaRepository.findByProductoIdAndActivoTrue(productoId);
    }

    public List<Resena> listarPorPerfil(Long perfilId) {
        perfilClient.buscarPerfilPorId(perfilId);
        return resenaRepository.findByPerfilIdAndActivoTrue(perfilId);
    }

    public Resena actualizar(Long id, ActualizarResenaRequest request) {

        Resena resena = buscarPorId(id);

        resena.setPuntuacion(request.getPuntuacion());
        resena.setComentario(request.getComentario());

        return resenaRepository.save(resena);
    }

    public void eliminar(Long id) {

        Resena resena = buscarPorId(id);

        resena.setActivo(false);

        resenaRepository.save(resena);
    }

    public Double obtenerPromedioPorProducto(Long productoId) {
        productoClient.buscarProductoPorId(productoId);

        List<Resena> resenas = resenaRepository.findByProductoIdAndActivoTrue(productoId);

        if (resenas.isEmpty()) {
            return 0.0;
        }

        return resenas.stream()
                .mapToInt(Resena::getPuntuacion)
                .average()
                .orElse(0.0);
    }
}