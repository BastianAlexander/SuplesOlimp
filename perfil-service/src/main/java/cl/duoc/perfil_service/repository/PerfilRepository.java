package cl.duoc.perfil_service.repository;

import cl.duoc.perfil_service.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Perfil findByAuth(Long auth);

}
