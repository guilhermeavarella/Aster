package com.aster.aster_dashboard_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalVendasPacoteDto {

    private String pacote;
    private Long vendas;
}
