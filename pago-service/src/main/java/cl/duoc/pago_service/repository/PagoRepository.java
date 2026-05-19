package cl.duoc.pago_service.repository;

import cl.duoc.pago_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByOrdenId(Long ordenId);

    boolean existsByOrdenId(Long ordenId);

    List<Pago> findByEstadoIgnoreCase(String estado);

    List<Pago> findByMetodoIgnoreCase(String metodo);
}