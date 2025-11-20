package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVersaoDto {

    private String numeroVersao;
    private String produtoId;
    private LocalDate dataLancamento;
    private String arquivoInstalador;
    private String patchNotes;
}
