package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ClienteOrganizacaoConverter;
import com.aster.aster_dashboard_backend.dto.ClienteOrganizacaoDto;
import com.aster.aster_dashboard_backend.entity.Organizacao;
import com.aster.aster_dashboard_backend.repository.ClienteOrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteOrganizacaoService {

    private ClienteOrganizacaoRepository repository;
    private ClienteOrganizacaoConverter converter;

    @Autowired
    public ClienteOrganizacaoService(ClienteOrganizacaoRepository repository, ClienteOrganizacaoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<ClienteOrganizacaoDto> findAllPaginated(int page) {
        Page<Organizacao> clientesOrganizacoes = repository.findAll(PageRequest.of(page, 15));
        return clientesOrganizacoes.map(converter::toDto);
    }

    public ClienteOrganizacaoDto findByDocumento(String documento) {

        Optional<Organizacao> result =  repository.findById(documento);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse documento!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(ClienteOrganizacaoDto dto) {

        if (dto.getDocumento() == null) {
            throw new IllegalArgumentException("Um documento deve ser fornecido!");
        }

        if (repository.existsById(dto.getDocumento())) {
            throw new IllegalArgumentException("Esse documento já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String documento, ClienteOrganizacaoDto dto) {
        Optional<Organizacao> result = repository.findById(documento);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse documento!");
        }

        Organizacao entity = result.get();

        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }

        if (dto.getRegiao() != null) {
            entity.setRegiao(dto.getRegiao());
        }

        if (dto.getContinente() != null) {
            entity.setContinente(dto.getContinente());
        }

        if (dto.getTelefone() != null) {
            entity.setTelefone(dto.getTelefone());
        }

        if (dto.getPorte() != null) {
            entity.setPorte(dto.getPorte());
        }

        if (dto.getSetorAtuacao()  != null) {
            entity.setSetorAtuacao(dto.getSetorAtuacao());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String documento) {
        Optional<Organizacao> result = repository.findById(documento);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse documento!");
        }

        repository.delete(result.get());
    }


}
