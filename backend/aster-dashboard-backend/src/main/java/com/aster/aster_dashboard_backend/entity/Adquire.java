package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.AdquireId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="adquire")
public class Adquire {

    @EmbeddedId
    private AdquireId id;

    @ManyToOne
    @MapsId("pacoteNome")
    @JoinColumn(name="pacote_nome")
    private Pacote pacote;

    @ManyToOne
    @MapsId("clienteDocumento")
    @JoinColumn(name="cliente_documento")
    private Cliente cliente;

    @ManyToOne
    @MapsId("licencaId")
    @JoinColumn(name="licenca_id")
    private Licenca licenca;


    @Override
    public String toString() {
        return "Adquire{" +
                "id=" + id +
                '}';
    }
}
