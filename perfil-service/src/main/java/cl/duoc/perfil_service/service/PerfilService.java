    package cl.duoc.perfil_service.service;

    import cl.duoc.perfil_service.dto.ActualizarPerfilRequest;
    import cl.duoc.perfil_service.dto.CrearPerfilRequest;
    import cl.duoc.perfil_service.model.Perfil;
    import cl.duoc.perfil_service.repository.PerfilRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class PerfilService {

        private final PerfilRepository perfilRepository;

        public Perfil crear(CrearPerfilRequest request) {

            if (perfilRepository.existsByUsuarioAuthId(request.getUsuarioAuthId())) {
                throw new RuntimeException("Este usuario ya tiene un perfil creado");
            }

            Perfil perfil = Perfil.builder()
                    .usuarioAuthId(request.getUsuarioAuthId())
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .telefono(request.getTelefono())
                    .direccion(request.getDireccion())
                    .activo(true)
                    .build();

            return perfilRepository.save(perfil);
        }

        public List<Perfil> listar() {
            return perfilRepository.findByActivoTrue();
        }

        public Perfil buscarPorId(Long id) {
            return perfilRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        }

        public Perfil buscarPorUsuarioAuthId(Long usuarioAuthId) {
            return perfilRepository.findByUsuarioAuthId(usuarioAuthId)
                    .orElseThrow(() -> new RuntimeException("Perfil no encontrado para este usuario"));
        }

        public Perfil actualizar(Long id, ActualizarPerfilRequest request) {

            Perfil perfil = buscarPorId(id);

            perfil.setNombre(request.getNombre());
            perfil.setApellido(request.getApellido());
            perfil.setTelefono(request.getTelefono());
            perfil.setDireccion(request.getDireccion());

            return perfilRepository.save(perfil);
        }

        public void eliminar(Long id) {

            Perfil perfil = buscarPorId(id);

            perfil.setActivo(false);

            perfilRepository.save(perfil);
        }

        public Perfil actualizarDireccion(Long id, String direccion) {

            Perfil perfil = buscarPorId(id);

            perfil.setDireccion(direccion);

            return perfilRepository.save(perfil);
        }

        public Perfil actualizarTelefono(Long id, String telefono) {

            Perfil perfil = buscarPorId(id);

            perfil.setTelefono(telefono);

            return perfilRepository.save(perfil);
        }
    }