package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.UsuarioDto;
import com.aster.aster_dashboard_backend.entity.Usuario;
import com.aster.aster_dashboard_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<UsuarioDto> findAllPaginated(int page) {
        Page<Usuario> usuarios = repository.findAll(PageRequest.of(page, 15));
        return usuarios.map(UsuarioDto::new);
    }

    public void create(UsuarioDto dto) {
        if (dto.getChaveUso() == null) {
            throw new IllegalArgumentException("Uma chave de uso deve ser fornecida!");
        }

        if (repository.existsById(dto.getChaveUso())) {
            throw new RuntimeException("Essa chave de uso já existe!");
        }

        Usuario usuario = new Usuario(dto.getChaveUso());

        repository.save(usuario);
    }
}
