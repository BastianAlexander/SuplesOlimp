package cl.duoc.auth_service.service;

import cl.duoc.auth_service.dto.ActualizarAuthDto;
import cl.duoc.auth_service.dto.AuthDto;
import cl.duoc.auth_service.exception.ContrasenaCorreoIncorrecto;
import cl.duoc.auth_service.exception.UsuarioNoExiste;
import cl.duoc.auth_service.mapper.AuthMapper;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthMapper authMapper;

    public List<Auth> findAll(){
        return authRepository.findAll();
    }

    public Auth findById(Long id){
        return authRepository.findById(id).orElse(null);
    }

    public Auth save(Auth auth){
        auth.setActivo(true);
        auth.setFechaCreacion(LocalDateTime.now());
        return authRepository.save(auth);
    }
    public Auth update(Long id, ActualizarAuthDto actualizarAuthDto){
        Auth Authactualizar = authRepository.findById(id).orElse(null);
        if (Authactualizar == null){
            throw new UsuarioNoExiste("Usuario no existe!");
        }
        if (!Authactualizar.getPassword().equals(actualizarAuthDto.getContrasennaActual())) {
            throw new ContrasenaCorreoIncorrecto("La contraseña actual es incorrecta");
        }
        Authactualizar.setCorreo(actualizarAuthDto.getCorreo());
        Authactualizar.setPassword(actualizarAuthDto.getPassword());
        return authRepository.save(Authactualizar);
    }
    public void delete(Long id){
        authRepository.deleteById(id);
    }

    public AuthDto findDTO(Long id){
        return authMapper.toDTO(findById(id));
    }

    public List<AuthDto> findDTOList(){
        return authMapper.toDTOlist(findAll());
    }

    public AuthDto login(String correo, String password) {
        Auth auth = authRepository.findByCorreo(correo);

        if (auth == null){
            throw new ContrasenaCorreoIncorrecto(" Error!! contraseña o correo incorrecto");
        }
        if (!auth.getPassword().equals(password)){
            throw new ContrasenaCorreoIncorrecto(" Error!! contraseña o correo incorrecto");
        }

        return authMapper.toDTO(auth);
    }

    public LocalDateTime obtenerFechaCreacion(Long id){
        Auth auth = authRepository.findById(id).orElse(null);

        if(auth == null){
            throw new UsuarioNoExiste("Usuario no encontrado");
        }

        return auth.getFechaCreacion();
    }

    public AuthDto findByCorreo(String correo){
        Auth auth = authRepository.findByCorreo(correo);

        if(auth == null){
            throw new ContrasenaCorreoIncorrecto("No existe un usuario con ese correo");
        }

        return authMapper.toDTO(auth);
    }

}
