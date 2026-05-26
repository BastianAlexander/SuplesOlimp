package cl.duoc.perfil_service.service;

import cl.duoc.perfil_service.clients.AuthFeign;
import cl.duoc.perfil_service.dto.ActualizarPerfilDTO;
import cl.duoc.perfil_service.dto.AuthDTO;
import cl.duoc.perfil_service.dto.PerfilDTO;
import cl.duoc.perfil_service.exception.UsuarioPerfilNoExiste;
import cl.duoc.perfil_service.mapper.PerfilMapper;
import cl.duoc.perfil_service.model.Perfil;
import cl.duoc.perfil_service.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private PerfilMapper perfilMapper;

    @Autowired
    private AuthFeign authFeign;

    public PerfilDTO findById(Long id){

        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new UsuarioPerfilNoExiste("El perfil o Usuario con ID " + id + " no existe"));
        PerfilDTO dto = perfilMapper.toDTO(perfil);
        AuthDTO auth = authFeign.buscarAuthPorId(perfil.getAuth());
        dto.setAuth(auth);

        return dto;
    }
    public Perfil save(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    public Perfil update(Long id, ActualizarPerfilDTO actualizarPerfilDTO){
        Perfil perfilActualizar = perfilRepository.findById(id).orElseThrow(() -> new UsuarioPerfilNoExiste("El perfil o Usuario con ID " + id + " no existe"));

        perfilActualizar.setNombre(actualizarPerfilDTO.getNombre());
        perfilActualizar.setApellido(actualizarPerfilDTO.getApellido());
        perfilActualizar.setTelefono(actualizarPerfilDTO.getTelefono());
        perfilActualizar.setDireccion(actualizarPerfilDTO.getDireccion());
        perfilActualizar.setUltimaModificacion(LocalDateTime.now());
        return perfilRepository.save(perfilActualizar);
    }
    public void delete(Long id){
        perfilRepository.deleteById(id);
    }

    public List<PerfilDTO> listado(){

        List<Perfil> listado = perfilRepository.findAll();
        List<PerfilDTO> listadoDTO = new ArrayList<>();

        for (Perfil perfil : listado) {

            PerfilDTO dto = perfilMapper.toDTO(perfil);
            AuthDTO auth = authFeign.buscarAuthPorId(perfil.getAuth());
            dto.setAuth(auth);
            listadoDTO.add(dto);
        }

        return listadoDTO;
    }

    public PerfilDTO buscarPorAuthId(Long authId) {
        Perfil perfil = perfilRepository.findByAuth(authId);
        if (perfil == null) {
            throw new UsuarioPerfilNoExiste(
                    "No existe perfil asociado al auth ID " + authId
            );
        }

        PerfilDTO dto = perfilMapper.toDTO(perfil);
        AuthDTO auth = authFeign.buscarAuthPorId(perfil.getAuth());
        dto.setAuth(auth);
        return dto;
    }

    public LocalDateTime obtenerUltimaModificacion(Long id){

        Perfil perfil = perfilRepository.findById(id).orElseThrow(() -> new UsuarioPerfilNoExiste("El perfil o Usuario con ID " + id + " no existe"));


        return perfil.getUltimaModificacion();
    }

    public PerfilDTO buscarPorCorreo(String correo){

        AuthDTO auth = authFeign.buscarPorCorreo(correo);

        if(auth == null){
            throw new UsuarioPerfilNoExiste(
                    "No existe usuario auth con correo " + correo
            );
        }

        Perfil perfil = perfilRepository.findByAuth(auth.getId());

        if (perfil == null) {
            throw new UsuarioPerfilNoExiste(
                    "No existe perfil asociado al correo " + correo
            );
        }

        PerfilDTO dto = perfilMapper.toDTO(perfil);

        dto.setAuth(auth);

        return dto;
    }


}
