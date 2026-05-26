package cl.duoc.orden_service.repository;

import cl.duoc.orden_service.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

    List<Orden> findByClienteId(Long clienteId);

    List<Orden> findByEstadoIgnoreCase(String estado);

    boolean existsByCarritoId(Long carritoId);
}