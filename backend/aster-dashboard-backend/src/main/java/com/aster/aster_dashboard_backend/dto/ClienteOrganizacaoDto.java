package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOrganizacaoDto {

    private String documento;
    private String nome;
    private String email;
    private String regiao;
    private String telefone;

    private String porte;
    private String setorAtuacao;
}
