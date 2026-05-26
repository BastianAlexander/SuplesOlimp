package cl.duoc.auth_service.mapper;

import cl.duoc.auth_service.dto.AuthDto;
import cl.duoc.auth_service.model.Auth;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthMapper {

    public AuthDto toDTO(Auth auth){
        if(auth ==null) return null;

        AuthDto dto = new AuthDto();
        dto.setId(auth.getId());
        String correo = auth.getCorreo();
        String oculto = correo.substring(0,4) + "****" + correo.substring(correo.indexOf("@"));
        dto.setCorreo(oculto);
        return dto;
    }

    public List<AuthDto> toDTOlist(List<Auth> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
