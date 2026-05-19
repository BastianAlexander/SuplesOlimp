package cl.duoc.orden_service.repository;

import cl.duoc.orden_service.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

    List<Orden> findByPerfilId(Long perfilId);

    List<Orden> findByEstadoIgnoreCase(String estado);
}