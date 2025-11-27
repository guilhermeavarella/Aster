package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacoteComProdutosDto {

    private String nome;
    private BigDecimal precoIndividual;
    private BigDecimal precoOrganizacional;
    private List<IdNomeProdutoDto> produtos;
}
