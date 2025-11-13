package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ProdutoConverter;
import com.aster.aster_dashboard_backend.dto.ProdutoDto;
import com.aster.aster_dashboard_backend.dto.ProdutoInicioDto;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository repository;
    private ProdutoConverter converter;

    @Autowired
    public ProdutoService(ProdutoRepository repository, ProdutoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<ProdutoInicioDto> findProdutosInicio() {
        return repository.findProdutosInicio();
    }

    public Page<ProdutoDto> findAllPaginated(int page) {
        Page<Produto> produtos = repository.findAll(PageRequest.of(page, 15));

        return produtos.map(converter::toDto);
    }
}
