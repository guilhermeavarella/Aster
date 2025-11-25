package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaMensalProdutoDto {

    private String produto;
    private Date data;
    private BigDecimal receita;
}
