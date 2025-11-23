package com.aster.aster_dashboard_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AdquireDto {
    
    private List<String> licencaId;
    private String pacoteNome;
    private String clienteDocumento;

}
