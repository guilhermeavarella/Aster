package com.aster.aster_dashboard_backend.service;

import com.aster.aster_dashboard_backend.converter.ProdutoVersaoConverter;
import com.aster.aster_dashboard_backend.dto.ProdutoVersaoDto;
import com.aster.aster_dashboard_backend.entity.ProdutoVersao;
import com.aster.aster_dashboard_backend.entity.id.ProdutoVersaoId;
import com.aster.aster_dashboard_backend.repository.ProdutoVersaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoVersaoService {

    private ProdutoVersaoRepository repository;
    private ProdutoVersaoConverter converter;

    @Autowired
    public ProdutoVersaoService(ProdutoVersaoRepository repository, ProdutoVersaoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Page<ProdutoVersaoDto> findAllPaginated(int page) {
        Page<ProdutoVersao> versoes = repository.findAll(PageRequest.of(page, 15));
        return versoes.map(converter::toDto);
    }

    public ProdutoVersaoDto findById(String numeroVersao, String produtoId) {
        ProdutoVersaoId id = new ProdutoVersaoId(numeroVersao, produtoId);
        Optional<ProdutoVersao> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse número de versão e ID de produto!");
        }

        return converter.toDto(result.get());
    }

    @Transactional
    public void create(ProdutoVersaoDto dto) {

        if (dto.getNumeroVersao() == null) {
            throw new IllegalArgumentException("Um número de versão deve ser fornecido!");
        }
        if (dto.getProdutoId() == null) {
            throw new IllegalArgumentException("Um ID de produto deve ser fornecido!");
        }

        ProdutoVersaoId id = new ProdutoVersaoId(dto.getNumeroVersao(), dto.getProdutoId());

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Já existe um registro com esse número de versão e ID de produto!");
        }

        repository.save(converter.toEntity(dto));
    }

    @Transactional
    public void update(String numeroVersao, String produtoId, ProdutoVersaoDto dto) {

        if (numeroVersao == null) {
            throw new IllegalArgumentException("Um número de versão deve ser fornecido!");
        }
        if (produtoId == null) {
            throw new IllegalArgumentException("Um ID de produto deve ser fornecido!");
        }

        ProdutoVersaoId id = new ProdutoVersaoId(numeroVersao, produtoId);

        Optional<ProdutoVersao> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse número de versão e ID de produto!");
        }

        ProdutoVersao entity = result.get();

        if (dto.getDataLancamento() != null) {
            entity.setDataLancamento(dto.getDataLancamento());
        }

        if (dto.getArquivoInstalador() != null) {
            entity.setArquivoInstalador(dto.getArquivoInstalador());
        }

        if (dto.getPatchNotes() != null) {
            entity.setPatchNotes(dto.getPatchNotes());
        }

        repository.save(entity);
    }

    @Transactional
    public void delete(String numeroVersao, String produtoId) {

        if (numeroVersao == null) {
            throw new IllegalArgumentException("Um número de versão deve ser fornecido!");
        }
        if (produtoId == null) {
            throw new IllegalArgumentException("Um ID de produto deve ser fornecido!");
        }

        ProdutoVersaoId id = new ProdutoVersaoId(numeroVersao, produtoId);

        Optional<ProdutoVersao> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new RuntimeException("Não há nenhum registro com esse número de versão e ID de produto!");
        }

        repository.delete(result.get());
    }
}
