package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.dto.ProdutoInicioDto;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        repository = produtoRepository;
    }

    public List<ProdutoInicioDto> findProdutosInicio() {
        return repository.findProdutosInicio();
    }
}
