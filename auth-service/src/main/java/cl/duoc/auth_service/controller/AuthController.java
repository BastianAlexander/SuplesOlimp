package cl.duoc.auth_service.controller;

import cl.duoc.auth_service.dto.ActualizarAuthDto;
import cl.duoc.auth_service.dto.AuthDto;
import cl.duoc.auth_service.exception.UsuarioNoExiste;
import cl.duoc.auth_service.model.Auth;
import cl.duoc.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(authService.findDTOList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        AuthDto authDto = authService.findDTO(id);
        if (authDto == null){
            throw new UsuarioNoExiste("Usuario No Existe");
        }
        return ResponseEntity.ok(authDto);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Auth auth){
        Auth authnuevo = authService.save(auth);
        return new ResponseEntity<>(authService.findDTO(authnuevo.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ActualizarAuthDto actualizarAuthDto){
        Auth authActualizado = authService.update(id,actualizarAuthDto);
        if (authActualizado == null){
            throw new UsuarioNoExiste("Usuario No Existe");
        }
        return ResponseEntity.ok("Usuario Actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }


    //Endpoints extrasss
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Auth auth) {
        AuthDto authDto = authService.login(auth.getCorreo(), auth.getPassword());
        return ResponseEntity.ok("Logeado exitosamente");

    }

    @GetMapping("/{id}/fecha-creacion")
    public ResponseEntity<?> obtenerFecha(@PathVariable Long id){
        return ResponseEntity.ok(authService.obtenerFechaCreacion(id));
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo){
        return ResponseEntity.ok(authService.findByCorreo(correo));
    }



}
