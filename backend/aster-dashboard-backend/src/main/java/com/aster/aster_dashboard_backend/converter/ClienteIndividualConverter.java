package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.ClienteIndividualDto;
import com.aster.aster_dashboard_backend.entity.Individual;
import org.springframework.stereotype.Component;

@Component
public class ClienteIndividualConverter {

    public ClienteIndividualDto toDto(Individual entity) {
        ClienteIndividualDto dto = new ClienteIndividualDto();

        return dto.builder()
                .documento(entity.getDocumento())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .regiao(entity.getRegiao())
                .continente(entity.getContinente())
                .telefone(entity.getTelefone())
                .atividadeUso(entity.getAtividadeUso())
                .build();
    }

    public Individual toEntity(ClienteIndividualDto dto) {
        Individual entity = new Individual();

        entity.setDocumento(dto.getDocumento());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setRegiao(dto.getRegiao());
        entity.setContinente(dto.getContinente());
        entity.setTelefone(dto.getTelefone());
        entity.setAtividadeUso(dto.getAtividadeUso());

        return entity;
    }
}
