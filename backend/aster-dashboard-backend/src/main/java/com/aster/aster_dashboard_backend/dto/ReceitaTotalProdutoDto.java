package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaTotalProdutoDto {

    private String produto;
    private BigDecimal receita;
}
