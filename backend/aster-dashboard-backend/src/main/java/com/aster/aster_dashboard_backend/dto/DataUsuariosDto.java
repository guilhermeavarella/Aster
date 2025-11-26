package com.aster.aster_dashboard_backend.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataUsuariosDto {

    private Date data;
    private Long usuarios;
}
