package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ClienteIndividualConverter;
import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.entity.Individual;
import com.aster.aster_dashboard_backend.repository.ClienteIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteIndividualService {

    private final ClienteIndividualRepository repository;
    private ClienteIndividualConverter converter;

    @Autowired
    public ClienteIndividualService(ClienteIndividualRepository repository, ClienteIndividualConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<ClienteIndividualDto> findAllPaginated(int page) {
        Page<Individual> clientesIndividuais = repository.findAll(PageRequest.of(page, 15));
        return clientesIndividuais.map(converter::toDto);
    }

    public ClienteIndividualDto findByDocumento(String documento) {

        Optional<Individual> result = repository.findById(documento);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(ClienteIndividualDto dto) {

        if (dto.getDocumento() == null) {
            throw new IllegalArgumentException("Um documento deve ser fornecido!");
        }

        if (repository.existsById(dto.getDocumento())) {
            throw new IllegalArgumentException("Esse documento já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String documento, ClienteIndividualDto dto) {
        Optional<Individual> result = repository.findById(documento);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse documento!");
        }

        Individual entity = result.get();

        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }

        if (dto.getRegiao() != null) {
            entity.setRegiao(dto.getRegiao());
        }

        if  (dto.getContinente() != null) {
            entity.setContinente(dto.getContinente());
        }

        if (dto.getTelefone() != null) {
            entity.setTelefone(dto.getTelefone());
        }

        if (dto.getAtividadeUso() != null) {
            entity.setAtividadeUso(dto.getAtividadeUso());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String documento) {
        Optional<Individual> result = repository.findById(documento);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse documento!");
        }

        repository.delete(result.get());
    }


}
