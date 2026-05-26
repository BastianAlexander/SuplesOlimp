package cl.duoc.producto_service.service;

import cl.duoc.producto_service.exception.CategoriaNoExiste;
import cl.duoc.producto_service.model.Categoria;
import cl.duoc.producto_service.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if (categoria == null) {
            throw new CategoriaNoExiste("No existe la categoría con ID: " + id);
        }

        return categoria;
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        Categoria categoriaActualizar = categoriaRepository.findById(id).orElse(null);

        if (categoriaActualizar == null) {
            throw new CategoriaNoExiste("No existe la categoría con ID: " + id);
        }

        categoriaActualizar.setNombre(categoria.getNombre());

        return categoriaRepository.save(categoriaActualizar);
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}