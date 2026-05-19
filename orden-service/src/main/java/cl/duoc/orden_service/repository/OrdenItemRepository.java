package cl.duoc.orden_service.repository;

import cl.duoc.orden_service.model.OrdenItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenItemRepository extends JpaRepository<OrdenItem, Long> {

    List<OrdenItem> findByOrdenId(Long ordenId);
}