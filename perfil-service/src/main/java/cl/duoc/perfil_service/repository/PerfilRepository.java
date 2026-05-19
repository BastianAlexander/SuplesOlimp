package cl.duoc.perfil_service.repository;

import cl.duoc.perfil_service.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByUsuarioAuthId(Long usuarioAuthId);

    boolean existsByUsuarioAuthId(Long usuarioAuthId);

    List<Perfil> findByActivoTrue();
}