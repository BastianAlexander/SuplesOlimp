package cl.duoc.carrito_service.repository;

import cl.duoc.carrito_service.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByCarritoId(Long carritoId);

    @Modifying
    @Query("DELETE FROM CarritoItem ci WHERE ci.carritoId = :carritoId")
    void eliminarItemsPorCarritoId(Long carritoId);
}