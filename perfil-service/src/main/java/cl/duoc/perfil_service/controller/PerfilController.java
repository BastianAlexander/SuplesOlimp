package cl.duoc.perfil_service.controller;

import cl.duoc.perfil_service.dto.ActualizarPerfilDTO;
import cl.duoc.perfil_service.dto.PerfilDTO;
import cl.duoc.perfil_service.model.Perfil;
import cl.duoc.perfil_service.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/perfiles")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping()
    public ResponseEntity<?> listaPerfiles(){
        return ResponseEntity.ok(perfilService.listado());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        PerfilDTO perfilDTO  = perfilService.findById(id);
        if (perfilDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(perfilDTO);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Perfil perfil){
        Perfil perfilNuevo = perfilService.save(perfil);
        return new ResponseEntity<>(perfilNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ActualizarPerfilDTO perfil){
        Perfil perfilActualizado  = perfilService.update(id,perfil );
        if (perfilActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(perfilActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        perfilService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Endpoints extrassss


    @GetMapping("/auth/{authId}")
    public ResponseEntity<PerfilDTO> buscarPorAuthId(@PathVariable Long authId) {
        PerfilDTO perfil = perfilService.buscarPorAuthId(authId);

        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/ultima-modificacion/{id}")
    public ResponseEntity<LocalDateTime> obtenerUltimaModificacion(
            @PathVariable Long id){
        LocalDateTime fecha = perfilService.obtenerUltimaModificacion(id);
        if(fecha == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fecha);
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<PerfilDTO> buscarPorCorreo(
            @PathVariable String correo){

        PerfilDTO perfil = perfilService.buscarPorCorreo(correo);

        if(perfil == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfil);
    }


}
