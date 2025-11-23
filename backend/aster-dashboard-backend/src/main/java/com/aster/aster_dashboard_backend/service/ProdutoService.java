package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ProdutoConverter;
import com.aster.aster_dashboard_backend.dto.*;
import com.aster.aster_dashboard_backend.entity.Categoria;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.id.CategoriaId;
import com.aster.aster_dashboard_backend.repository.CategoriaRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ProdutoDto findById(String id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID!");
        }

        return converter.toDto(produto.get());

    }

    public List<TotalVendasProdutoDto> findTotalVendasProduto() {
        return repository.findTotalVendasProduto();
    }

    public List<VendasMensaisProdutoDto> findVendasMensaisProduto() {
        return repository.findVendasMensaisProduto();
    }

    public List<ReceitaTotalProdutoDto> findReceitaTotalProduto() {
        return repository.findReceitaTotalProduto();
    }

    public List<ReceitaMensalProdutoDto> findReceitaMensalProduto() {
        return repository.findReceitaMensalProduto();
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

    @Transactional
    public void update(String id, ProdutoDto dto) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Esse ID não existe!");
        }

        Produto entity = produto.get();

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (dto.getIcone() != null) {
            entity.setIcone(dto.getIcone());
        }

        if (dto.getDescricaoBreve() != null) {
            entity.setDescricaoBreve(dto.getDescricaoBreve());
        }

        if (dto.getDescricao() != null) {
            entity.setDescricaoCompleta(dto.getDescricao());
        }

        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }

        repository.save(entity);

        if (dto.getCategorias() != null) {

            for (String categoria : categoriaRepository.findNomesByProdutoId(entity.getId())) {
                CategoriaId categoriaId = new CategoriaId(categoria, entity.getId());
                Categoria categoriaEntity = categoriaRepository.findById(categoriaId).orElse(null);

                if (categoriaEntity != null) {
                    categoriaRepository.delete(categoriaEntity);
                }
            }
            List<String> novasCategorias = dto.getCategorias();

            for (String categoria : novasCategorias) {
                CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
                Categoria novaCategoria = new Categoria(categoriaId, entity);

                categoriaRepository.save(novaCategoria);
            }
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Esse ID não existe!");
        }

        Produto entity = produto.get();
        ProdutoDto dto = converter.toDto(entity);

        for (String categoria : dto.getCategorias()) {
            CategoriaId categoriaId = new CategoriaId(categoria, dto.getId());
            Categoria categoriaEntity = categoriaRepository.findById(categoriaId).orElse(null);

            if (categoriaEntity != null) {
                categoriaRepository.delete(categoriaEntity);
            }
        }

        repository.delete(entity);
    }
}
