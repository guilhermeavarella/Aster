package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="cliente_documento")
@Table(name="individual")
public class Individual extends Cliente {

    @Column(name="atividade_uso")
    private String atividadeUso;

    public Individual(String documento, String nome, String email, String regiao, String telefone, String continente, String atividadeUso) {
        super(documento, nome, email, regiao, continente, telefone);
        this.atividadeUso = atividadeUso;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "atividadeUso='" + atividadeUso + '\'' +
                '}';
    }
}
