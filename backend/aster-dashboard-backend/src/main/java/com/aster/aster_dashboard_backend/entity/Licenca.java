package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="licenca")
public class Licenca {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="tipo")
    private String tipo;

    @Column(name="data_registro")
    private LocalDate dataRegistro;

    @Column(name="ativa")
    private Boolean ativa;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    @Override
    public String toString() {
        return "Licenca{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dataRegistro=" + dataRegistro +
                ", ativa=" + ativa +
                ", produtoId=" + produto.getId() +
                '}';
    }
}
