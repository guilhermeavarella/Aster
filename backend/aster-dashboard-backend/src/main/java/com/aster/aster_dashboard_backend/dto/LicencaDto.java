package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LicencaDto {

    private String id;
    private Boolean ativa;
    private LocalDate dataRegistro;
    private String tipo;
}
