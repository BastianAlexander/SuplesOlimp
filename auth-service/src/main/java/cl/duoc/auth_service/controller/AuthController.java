package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.dto.AuthResponse;
import cl.duoc.auth_service.dto.LoginRequest;
import cl.duoc.auth_service.dto.RegisterRequest;
import cl.duoc.auth_service.model.Usuario;
import cl.duoc.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.duoc.auth_service.dto.UsuarioResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String respuesta = authService.register(request);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse respuesta = authService.login(request);
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("Token válido, usuario autenticado");
    }


    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeUsuario(@RequestParam String correo) {
        return ResponseEntity.ok(authService.existeUsuarioActivo(correo));
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(authService.buscarUsuarioPorCorreo(correo));
    }
}