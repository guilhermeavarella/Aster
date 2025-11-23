package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendasMensaisPacoteDto {

    private String pacote;
    private LocalDate data;
    private Long vendas;
}
