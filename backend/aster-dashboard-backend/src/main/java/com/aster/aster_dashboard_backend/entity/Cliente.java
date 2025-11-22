package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="cliente")
public abstract class Cliente{

    @Id
    @Column(name="documento")
    private String documento;
    
    @Column(name="nome")
    private String nome;

    @Column(name="email")
    private String email;

    @Column(name="regiao")
    private String regiao;

    @Column(name="continente")
    private String continente;

    @Column(name="telefone")
    private String telefone;

    @Override
    public String toString() {
        return "Cliente{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", regiao='" + regiao + '\'' +
                ", continente='" + continente + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
