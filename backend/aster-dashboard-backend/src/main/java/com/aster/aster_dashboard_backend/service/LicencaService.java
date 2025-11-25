package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.LicencaConverter;
import com.aster.aster_dashboard_backend.dto.LicencaDto;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.repository.LicencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LicencaService {

    private LicencaRepository repository;
    private LicencaConverter converter;

    @Autowired
    public LicencaService(LicencaRepository repository, LicencaConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<LicencaDto> findAllPaginated(int page) {
        Page<Licenca> licencas = repository.findAll(PageRequest.of(page, 15));
        return licencas.map(converter::toDto);
    }

    public LicencaDto findById(String id) {
        Optional<Licenca> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse ID!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(LicencaDto dto) {

        if (dto.getId() == null) {
            throw new IllegalArgumentException("Um ID deve ser fornecido!");
        }

        if (repository.existsById(dto.getId())) {
            throw new IllegalArgumentException("Esse ID já existe!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String id, LicencaDto dto) {
        Optional<Licenca> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse ID!");
        }

        Licenca entity = result.get();

        if (dto.getAtiva() != null) {
            entity.setAtiva(dto.getAtiva());
        }

        if (dto.getDataRegistro() != null) {
            entity.setDataRegistro(dto.getDataRegistro());
        }

        if (dto.getTipo() != null) {
            entity.setTipo(dto.getTipo());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String id) {
        Optional<Licenca> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Não há nenhum registro com esse ID!");
        }

        repository.delete(result.get());
    }
}
