package com.aster.aster_dashboard_backend.converter;

import com.aster.aster_dashboard_backend.dto.ClienteOrganizacaoDto;
import com.aster.aster_dashboard_backend.entity.Organizacao;
import org.springframework.stereotype.Component;

@Component
public class ClienteOrganizacaoConverter {

    public ClienteOrganizacaoDto toDto(Organizacao entity) {
        ClienteOrganizacaoDto dto = new ClienteOrganizacaoDto();

        return dto.builder()
                .documento(entity.getDocumento())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .regiao(entity.getRegiao())
                .continente(entity.getContinente())
                .telefone(entity.getTelefone())
                .porte(entity.getPorte())
                .setorAtuacao(entity.getSetorAtuacao())
                .build();
    }

    public Organizacao toEntity(ClienteOrganizacaoDto dto) {
        Organizacao entity = new Organizacao();

        entity.setDocumento(dto.getDocumento());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setRegiao(dto.getRegiao());
        entity.setContinente(dto.getContinente());
        entity.setTelefone(dto.getTelefone());
        entity.setPorte(dto.getPorte());
        entity.setSetorAtuacao(dto.getSetorAtuacao());
        return entity;
    }

}
