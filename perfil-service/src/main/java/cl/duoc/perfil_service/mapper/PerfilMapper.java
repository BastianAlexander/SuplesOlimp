package cl.duoc.perfil_service.mapper;

import cl.duoc.perfil_service.dto.PerfilDTO;
import cl.duoc.perfil_service.model.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {
    public PerfilDTO toDTO(Perfil perfil){
        if(perfil == null) return null;
        PerfilDTO dto = new PerfilDTO();

        dto.setId(perfil.getId());
        dto.setNombre(perfil.getNombre());
        dto.setApellido(perfil.getApellido());
        dto.setTelefono(perfil.getTelefono());
        dto.setDireccion(perfil.getDireccion());
        return dto;
    }
}
