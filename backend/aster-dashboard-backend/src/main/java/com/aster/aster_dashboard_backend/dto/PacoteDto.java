package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacoteDto {

    private String nome;
    private BigDecimal precoIndividual;
    private BigDecimal precoOrganizacional;
}
