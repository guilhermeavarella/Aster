package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.ContemDto;
import com.aster.aster_dashboard_backend.entity.Contem;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.id.ContemId;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContemConverter {

    private ProdutoRepository produtoRepository;
    private PacoteRepository pacoteRepository;

    @Autowired
    public ContemConverter(ProdutoRepository produtoRepository, PacoteRepository pacoteRepository) {
        this.pacoteRepository = pacoteRepository;
        this.produtoRepository = produtoRepository;
    }

    public ContemDto toDto(Contem entity) {
        ContemDto dto = new ContemDto();

        dto.setPacoteNome(entity.getId().getPacoteNome());
        dto.setProdutoId(entity.getId().getProdutoId());
        
        return dto;
    }

    public Contem toEntity(ContemDto dto) {
        Contem entity = new Contem();

        Optional<Produto> produto = produtoRepository.findById((dto.getProdutoId()));
        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Não existe um produto com esse ID!");
        }
        entity.setProduto(produto.get());

        Optional<Pacote> pacote = pacoteRepository.findById((dto.getPacoteNome()));
        if (pacote.isEmpty()) {
            throw new IllegalArgumentException("Não existe um produto com esse ID!");
        }
        entity.setPacote(pacote.get());

        ContemId id = new ContemId(dto.getPacoteNome(), dto.getProdutoId());
        entity.setId(id);

        return entity;
    }
}
