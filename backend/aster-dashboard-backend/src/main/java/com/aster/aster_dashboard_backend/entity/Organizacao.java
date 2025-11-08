package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="organizacao")
public class Organizacao extends Cliente {

    @Column(name="porte")
    private String porte;

    @Column(name="setor_atuacao")
    private String setorAtuacao;

    public Organizacao(String documento, String nome, String email, String regiao, String telefone, String porte, String setorAtuacao) {
        super(documento, nome, email, regiao, telefone);
        this.porte = porte;
        this.setorAtuacao = setorAtuacao;
    }

    @Override
    public String toString() {
        return "Organizacao{" +
                "porte='" + porte + '\'' +
                ", setorAtuacao='" + setorAtuacao + '\'' +
                '}';
    }
}
