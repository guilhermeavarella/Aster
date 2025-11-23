package com.aster.aster_dashboard_backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesPaisDto {

    private String pais;
    private Long clientes;

}
