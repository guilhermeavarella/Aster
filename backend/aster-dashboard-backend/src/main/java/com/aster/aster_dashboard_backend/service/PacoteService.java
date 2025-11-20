package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.PacoteConverter;
import com.aster.aster_dashboard_backend.dto.PacoteDto;
import com.aster.aster_dashboard_backend.entity.Pacote;
import com.aster.aster_dashboard_backend.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PacoteService {

    private PacoteRepository repository;
    private PacoteConverter converter;

    @Autowired
    public PacoteService(PacoteRepository repository, PacoteConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<PacoteDto> findAllPaginated(int page) {
        Page<Pacote> pacotes = repository.findAll(PageRequest.of(page, 15));
        return pacotes.map(converter::toDto);
    }

    public PacoteDto findByNome(String nome) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(PacoteDto dto) {

        if (dto.getNome() == null) {
            throw new IllegalArgumentException("Um nome deve ser fornecido!");
        }

        if (repository.existsById(dto.getNome())) {
            throw new IllegalArgumentException("Esse nome já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String nome, PacoteDto dto) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        Pacote entity = result.get();

        if (dto.getPrecoIndividual() != null) {
            entity.setPrecoIndividual(dto.getPrecoIndividual());
        }

        if (dto.getPrecoOrganizacional() != null) {
            entity.setPrecoOrganizacional(dto.getPrecoOrganizacional());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String nome) {
        Optional<Pacote> result = repository.findById(nome);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse nome!");
        }

        repository.delete(result.get());
    }
}
