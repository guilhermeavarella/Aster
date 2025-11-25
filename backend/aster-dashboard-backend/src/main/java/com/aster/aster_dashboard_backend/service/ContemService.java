package com.aster.aster_dashboard_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aster.aster_dashboard_backend.converter.ContemConverter;
import com.aster.aster_dashboard_backend.dto.ContemDto;
import com.aster.aster_dashboard_backend.entity.Contem;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.id.ContemId;
import com.aster.aster_dashboard_backend.repository.ContemRepository;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;

@Service
public class ContemService {

    private ContemRepository repository;
    private PacoteRepository pacoteRepository;
    private ProdutoRepository produtoRepository;
    private ContemConverter converter;

    @Autowired
    public ContemService(ContemRepository repository, PacoteRepository pacoteRepository,
            ProdutoRepository produtoRepository, ContemConverter converter) {
        this.pacoteRepository = pacoteRepository;
        this.produtoRepository = produtoRepository;
        this.repository = repository;
        this.converter = converter;
    }

    public List<ContemDto> findAll() {
        List<Contem> contem = repository.findAll();
        return contem.stream().map(converter::toDto).toList();
    }

    @Transactional
    public void create(ContemDto dto) {

        if (dto.getPacoteNome() == null) {
            throw new IllegalArgumentException("Um nome deve ser fornecido!");
        }

        if (dto.getProdutoId() == null) {
            throw new IllegalArgumentException("Um id deve ser fornecido!");
        }

        Optional<Pacote> pacote = pacoteRepository.findById(dto.getPacoteNome());
        if (pacote.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse nome de pacote!");
        }
        Optional<Produto> produto = produtoRepository.findById(dto.getProdutoId());
        if (produto.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de produto!");
        }

        ContemId id = new ContemId();
        id.setPacoteNome(dto.getPacoteNome());
        id.setProdutoId(dto.getProdutoId());

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Essa combinação já existe!");
        }

        repository.save(converter.toEntity(dto));
}

    @Transactional
    public void delete(String pacoteNome, String produtoId) {

        if (pacoteNome == null) {
            throw new IllegalArgumentException("Um nome de pacote deve ser fornecido!");
        }
        if (produtoId == null) {
            throw new IllegalArgumentException("Um ID de produto deve ser fornecido!");
        }

        Optional<Pacote> pacote = pacoteRepository.findById(pacoteNome);
        if (pacote.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse nome de pacote!");
        }
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        if (produto.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse ID de produto!");
        }

        ContemId id = new ContemId();
        id.setPacoteNome(pacoteNome);
        id.setProdutoId(produtoId);

        Optional<Contem> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse número de versão e ID de produto!");
        }

        repository.delete(result.get());
    }

}
