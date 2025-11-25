package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PorcentagemDto {

    private int id;
    private BigDecimal value;
    private String label;
}
