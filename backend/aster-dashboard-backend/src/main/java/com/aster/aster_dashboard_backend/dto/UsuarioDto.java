package com.aster.aster_dashboard_backend.dto;

import com.aster.aster_dashboard_backend.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String chaveUso;

    public UsuarioDto(Usuario usuario) {
        this.chaveUso = usuario.getChaveUso();
    }
}
