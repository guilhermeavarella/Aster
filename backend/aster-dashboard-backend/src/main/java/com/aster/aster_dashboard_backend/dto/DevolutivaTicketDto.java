package com.aster.aster_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DevolutivaTicketDto {

    private String id;
    private String assunto;
    private LocalDate dataEnvio;
    private String mensagem;
    private String produtoId;
    private String clienteDocumento;

    private String status;
    private String resposta;
}
