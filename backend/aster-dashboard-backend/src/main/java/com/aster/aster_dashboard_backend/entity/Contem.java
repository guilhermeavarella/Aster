package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.ContemId;

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
@Table(name="contem")
public class Contem {

    @EmbeddedId
    private ContemId id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name="produto_id")
    private Produto produto;

    @ManyToOne
    @MapsId("pacoteNome")
    @JoinColumn(name="pacote_nome")
    private Pacote pacote;

    @Override
    public String toString() {
        return "Contem{" +
                "id=" + id +
                ", produto=" + produto +
                ", pacote=" + pacote +
                '}';
    }
}
