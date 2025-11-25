package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pacote")
public class Pacote {

    @Id
    @Column(name="nome")
    private String nome;

    @Column(name="preco_organizacional", precision=8, scale=2)
    private BigDecimal precoOrganizacional;

    @Column(name="preco_individual", precision=8, scale=2)
    private BigDecimal precoIndividual;

    @Override
    public String toString() {
        return "Pacote{" +
                "nome='" + nome + '\'' +
                ", precoOrganizacional=" + precoOrganizacional +
                ", precoIndividual=" + precoIndividual +
                '}';
    }
}
