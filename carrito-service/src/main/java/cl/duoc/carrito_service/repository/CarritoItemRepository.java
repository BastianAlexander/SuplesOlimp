package cl.duoc.carrito_service.repository;

import cl.duoc.carrito_service.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByPerfilIdAndActivoTrue(Long perfilId);

    Optional<CarritoItem> findByPerfilIdAndProductoIdAndActivoTrue(Long perfilId, Long productoId);

    int countByPerfilIdAndActivoTrue(Long perfilId);
}