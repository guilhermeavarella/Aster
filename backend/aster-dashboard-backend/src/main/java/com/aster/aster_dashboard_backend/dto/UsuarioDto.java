package com.aster.aster_dashboard_backend.dto;

import com.aster.aster_dashboard_backend.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

    private String chaveUso;

    public UsuarioDto(Usuario usuario) {
        this.chaveUso = usuario.getChaveUso();
    }
}
