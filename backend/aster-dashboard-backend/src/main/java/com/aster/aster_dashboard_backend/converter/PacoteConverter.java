package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.PacoteDto;
import com.aster.aster_dashboard_backend.entity.Pacote;
import org.springframework.stereotype.Component;

@Component
public class PacoteConverter {

    public PacoteDto toDto(Pacote entity) {
        PacoteDto dto = new PacoteDto();

        return dto.builder()
                .nome(entity.getNome())
                .precoIndividual(entity.getPrecoIndividual())
                .precoOrganizacional(entity.getPrecoOrganizacional())
                .build();
    }

    public Pacote toEntity(PacoteDto dto) {
        Pacote entity = new Pacote();

        entity.setNome(dto.getNome());
        entity.setPrecoIndividual(dto.getPrecoIndividual());
        entity.setPrecoOrganizacional(dto.getPrecoOrganizacional());

        return entity;
    }
}
