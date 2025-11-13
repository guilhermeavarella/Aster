package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ProdutoConverter;
import com.aster.aster_dashboard_backend.dto.ProdutoDto;
import com.aster.aster_dashboard_backend.dto.ProdutoInicioDto;
import com.aster.aster_dashboard_backend.entity.Categoria;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.id.CategoriaId;
import com.aster.aster_dashboard_backend.repository.CategoriaRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository repository;
    private CategoriaRepository categoriaRepository;
    private ProdutoConverter converter;

    @Autowired
    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository, ProdutoConverter converter) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        this.converter = converter;
    }

    public List<ProdutoInicioDto> findProdutosInicio() {
        return repository.findProdutosInicio();
    }

    public Page<ProdutoDto> findAllPaginated(int page) {
        Page<Produto> produtos = repository.findAll(PageRequest.of(page, 15));

        return produtos.map(converter::toDto);
    }

    @Transactional
    public void create(ProdutoDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Um ID deve ser fornecido!");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Esse ID já existe!");
        }

        Produto produto = converter.toEntity(dto);
        repository.save(produto);

        List<String> categorias = dto.getCategorias();

        for (String categoria : categorias) {
            CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
            Categoria entity = new Categoria(categoriaId, produto);

            categoriaRepository.save(entity);
        }
    }
}
