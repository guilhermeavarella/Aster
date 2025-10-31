package com.aster.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="produto")
public class Produto {

    @Id
    @Column(name="rg")
    private String rg;

    @Column(name="nome")
    private String nome;

    @Column(name="endereco")
    private String endereco;

    @Override
    public String toString() {
        return "Autor{" +
                "rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
