package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private String id;
    private String nome;
    private String status;
    private String descricaoBreve;
    private String descricao;
    private String icone;

    private List<String> categorias;

}
