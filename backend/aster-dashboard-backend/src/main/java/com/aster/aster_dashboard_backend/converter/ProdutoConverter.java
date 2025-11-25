package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.ProdutoDto;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoConverter {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoConverter(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoDto toDto(Produto produto){
        ProdutoDto dto = new ProdutoDto();

        dto.setId(produto.getId());
        dto.setStatus(produto.getStatus());
        dto.setIcone(produto.getIcone());
        dto.setDescricaoBreve(produto.getDescricaoBreve());
        dto.setDescricao(produto.getDescricaoCompleta());
        dto.setNome(produto.getNome());

        List<String> categorias = categoriaRepository.findNomesByProdutoId(produto.getId());
        dto.setCategorias(categorias);

        return dto;
    }

    public Produto toEntity(ProdutoDto produtoDto){
        Produto produto = new Produto();

        produto.setId(produtoDto.getId());
        produto.setStatus(produtoDto.getStatus());
        produto.setIcone(produtoDto.getIcone());
        produto.setDescricaoBreve(produtoDto.getDescricaoBreve());
        produto.setDescricaoCompleta(produtoDto.getDescricao());
        produto.setNome(produtoDto.getNome());

        return produto;
    }
}
