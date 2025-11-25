package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaAvaliacoesPacoteDto {

    private String pacote;
    private BigDecimal avaliacao;
}
