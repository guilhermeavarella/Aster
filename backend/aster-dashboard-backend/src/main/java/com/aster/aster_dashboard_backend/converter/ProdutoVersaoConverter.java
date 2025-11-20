package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.ProdutoVersaoDto;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.entity.ProdutoVersao;
import com.aster.aster_dashboard_backend.entity.id.ProdutoVersaoId;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoVersaoConverter {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoVersaoConverter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoVersaoDto toDto(ProdutoVersao entity) {
        ProdutoVersaoDto dto = new ProdutoVersaoDto();

        return dto.builder()
                .numeroVersao(entity.getId().getNumeroVersao())
                .produtoId(entity.getProduto().getId())
                .dataLancamento(entity.getDataLancamento())
                .arquivoInstalador(entity.getArquivoInstalador())
                .patchNotes(entity.getPatchNotes())
                .build();
    }

    public ProdutoVersao toEntity(ProdutoVersaoDto dto) {
        ProdutoVersao entity = new ProdutoVersao();

        ProdutoVersaoId id = new ProdutoVersaoId(dto.getNumeroVersao(), dto.getProdutoId());

        entity.setId(id);
        entity.setDataLancamento(dto.getDataLancamento());
        entity.setArquivoInstalador(dto.getArquivoInstalador());
        entity.setPatchNotes(dto.getPatchNotes());

        Optional<Produto> produto = produtoRepository.findById(dto.getProdutoId());
        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Não existe um produto com esse ID!");
        }
        entity.setProduto(produto.get());

        return entity;
    }
}
