package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaMensalPacoteDto {

    private String pacote;
    private Date data;
    private BigDecimal receita;
}
