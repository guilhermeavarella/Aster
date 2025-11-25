package com.aster.aster_dashboard_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalVendasProdutoDto {

    private String produto;
    private Long vendas;

}
