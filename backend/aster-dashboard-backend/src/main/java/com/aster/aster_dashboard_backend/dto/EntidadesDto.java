package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntidadesDto {

    private List<QuantidadeDto> Licencas;
    private List<QuantidadeDto> Produtos;
    private List<QuantidadeDto> Versoes;
    private List<QuantidadeDto> Clientes;
    private List<QuantidadeDto> Pacotes;
    private List<QuantidadeDto> Devolutivas;
    private List<QuantidadeDto> Usuarios;

}
