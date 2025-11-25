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
public class AdquireId implements Serializable {

    @Column(name="pacote_nome")
    private String pacoteNome;

    @Column(name="cliente_documento")
    private String clienteDocumento;

    @Column(name="licenca_id")
    private String licencaId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AdquireId adquireId = (AdquireId) o;
        return Objects.equals(getPacoteNome(), adquireId.getPacoteNome()) && Objects.equals(getClienteDocumento(), adquireId.getClienteDocumento()) && Objects.equals(getLicencaId(), adquireId.getLicencaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPacoteNome(), getClienteDocumento(), getLicencaId());
    }
}
