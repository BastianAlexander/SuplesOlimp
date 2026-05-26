package cl.duoc.auth_service.repository;

import cl.duoc.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    Auth findByCorreo(String correo);


}
