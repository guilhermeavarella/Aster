package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataVendasDto {

    private Date data;
    private Long vendas;
}
