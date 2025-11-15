package com.aster.aster_dashboard_backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVersaoDto {

    private String numeroVersao;
    private LocalDate dataLancamento;
    private String arquivoInstalador;
    private String patchNotes;
}
