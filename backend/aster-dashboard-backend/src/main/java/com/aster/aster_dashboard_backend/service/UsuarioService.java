package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.UsuarioDto;
import com.aster.aster_dashboard_backend.dto.UsuariosMensaisProdutoDto;
import com.aster.aster_dashboard_backend.dto.UsuariosProdutoDto;
import com.aster.aster_dashboard_backend.entity.Usuario;
import com.aster.aster_dashboard_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

    public UsuarioDto findById(String id) {
        Optional<UsuarioDto> result = repository.findById(id).map(UsuarioDto::new);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com essa chave de uso!");
        }

        return result.get();
    }

    public List<UsuariosProdutoDto> findUsuariosProduto() {
        return repository.findUsuariosProduto();
    }

    public List<UsuariosMensaisProdutoDto> findUsuariosMensaisProduto() {
        List<Object[]> lista = repository.findUsuariosMensaisProduto();
        return lista.stream().map(o -> new UsuariosMensaisProdutoDto(
                (o[0].toString()),
                ((Date) o[1]),
                ((Long) o[2])
        )).toList();
    }

    @Transactional
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

    @Transactional
    public UsuarioDto update(String id, UsuarioDto dto) {
        Optional<Usuario> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Essa chave de uso não existe!");
        }

        Usuario entity = result.get();

        repository.delete(entity);

        Usuario update = new Usuario(dto.getChaveUso());

        Usuario updated = repository.save(update);

        return new UsuarioDto(updated.getChaveUso());
    }

    @Transactional
    public void delete(String id) {
        Optional<Usuario> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new RuntimeException("Essa chave de uso não existe!");
        }

        repository.delete(entity.get());
    }
}
