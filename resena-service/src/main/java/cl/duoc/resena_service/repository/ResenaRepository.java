    package cl.duoc.resena_service.repository;

    import cl.duoc.resena_service.model.Resena;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface ResenaRepository extends JpaRepository<Resena, Long> {

        List<Resena> findByActivoTrue();

        List<Resena> findByProductoIdAndActivoTrue(Long productoId);

        List<Resena> findByPerfilIdAndActivoTrue(Long perfilId);



    }