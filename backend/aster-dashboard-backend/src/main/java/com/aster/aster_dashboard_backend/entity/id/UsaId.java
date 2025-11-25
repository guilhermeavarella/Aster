package com.aster.aster_dashboard_backend.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsaId implements Serializable {

    @Column(name="usuario_chave_uso")
    private String usuarioChaveUso;

    @Column(name="cliente_documento")
    private String clienteDocumento;

    @Column(name="licenca_id")
    private String licencaId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsaId usaId = (UsaId) o;
        return Objects.equals(getUsuarioChaveUso(), usaId.getUsuarioChaveUso()) && Objects.equals(getClienteDocumento(), usaId.getClienteDocumento()) && Objects.equals(getLicencaId(), usaId.getLicencaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuarioChaveUso(), getClienteDocumento(), getLicencaId());
    }
}
