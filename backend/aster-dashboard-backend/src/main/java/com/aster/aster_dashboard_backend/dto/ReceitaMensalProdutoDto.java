package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaMensalProdutoDto {

    private String produto;
    private LocalDate data;
    private BigDecimal receita;
}
