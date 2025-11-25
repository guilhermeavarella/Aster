package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.LicencaDto;
import com.aster.aster_dashboard_backend.entity.Licenca;
import com.aster.aster_dashboard_backend.entity.Produto;
import com.aster.aster_dashboard_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LicencaConverter {

    private ProdutoRepository produtoRepository;

    @Autowired
    public LicencaConverter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public LicencaDto toDto(Licenca licenca) {
        LicencaDto dto = new LicencaDto();

        return dto.builder()
                .id(licenca.getId())
                .ativa(licenca.getAtiva())
                .dataRegistro(licenca.getDataRegistro())
                .tipo(licenca.getTipo())
                .produtoId(licenca.getProduto().getId())
                .build();
    }

    public Licenca toEntity(LicencaDto dto) {
        Licenca entity = new Licenca();

        entity.setId(dto.getId());
        entity.setAtiva(dto.getAtiva());
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setTipo(dto.getTipo());

        Optional<Produto> produto = produtoRepository.findById(dto.getProdutoId());
        if (produto.isEmpty()) {
            throw new IllegalArgumentException("Não existe um produto com esse ID!");
        }
        entity.setProduto(produto.get());

        return entity;
    }
}
