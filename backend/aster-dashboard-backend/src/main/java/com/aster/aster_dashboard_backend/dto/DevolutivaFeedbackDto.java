package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DevolutivaFeedbackDto {

    private String id;
    private String assunto;
    private LocalDate dataEnvio;
    private String mensagem;

    private BigDecimal avaliacao;
    private String atividadeUso;
}
