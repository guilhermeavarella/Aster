package com.aster.aster_dashboard_backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteIndividualDto {

    private String documento;
    private String nome;
    private String email;
    private String regiao;
    private String continente;
    private String telefone;

    private String atividadeUso;
}
