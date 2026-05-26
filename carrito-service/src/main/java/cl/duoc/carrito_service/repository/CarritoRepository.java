package cl.duoc.carrito_service.repository;

import cl.duoc.carrito_service.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByClienteId(Long clienteId);
}