package cl.duoc.producto_service.repository;

import cl.duoc.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByActivoTrue();

    List<Producto> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    List<Producto> findByMarcaContainingIgnoreCaseAndActivoTrue(String marca);
    List<Producto> findByPrecioBetweenAndActivoTrue(BigDecimal min, BigDecimal max);



}

