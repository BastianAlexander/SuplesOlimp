package cl.duoc.auth_service.service;
import cl.duoc.auth_service.dto.AuthResponse;
import cl.duoc.auth_service.security.JwtService;
import cl.duoc.auth_service.dto.LoginRequest;
import cl.duoc.auth_service.dto.RegisterRequest;
import cl.duoc.auth_service.model.Rol;
import cl.duoc.auth_service.model.Usuario;
import cl.duoc.auth_service.repository.RolRepository;
import cl.duoc.auth_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cl.duoc.auth_service.dto.UsuarioResponse;
import java.util.stream.Collectors;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            return "El correo ya está registrado";
        }

        Rol rolCliente = rolRepository.findByNombre("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));

        Usuario usuario = Usuario.builder()
                .correo(request.getCorreo())
                .password(passwordEncoder.encode(request.getPassword()))
                .activo(true)
                .roles(Set.of(rolCliente))
                .build();

        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }


    public AuthResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(usuario.getCorreo());

        return new AuthResponse(token, "Bearer", usuario.getCorreo());
    }

    public boolean existeUsuarioActivo(String correo) {
        return usuarioRepository.existsByCorreoAndActivoTrue(correo);
    }

    public UsuarioResponse buscarUsuarioPorCorreo(String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .correo(usuario.getCorreo())
                .activo(usuario.getActivo())
                .roles(usuario.getRoles()
                        .stream()
                        .map(rol -> rol.getNombre())
                        .collect(Collectors.toSet()))
                .build();
    }
}