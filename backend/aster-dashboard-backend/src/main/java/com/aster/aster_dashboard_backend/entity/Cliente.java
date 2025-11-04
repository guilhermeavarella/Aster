package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente{

    @Id
    @Column(name="documento", length=30, nullable=false)
    private String documento;
    
    @Column(name="nome", length=40, nullable=false)
    private String nome;

    @Column(name="email", length=30, nullable=false)
    private String email;

    @Column(name="regiao", length=30)
    private String regiao;

    @Column(name="telefone", length=30)
    private String telefone;

    @Override
    public String toString(){
        return "Cliente{" +
                "documento='" + documento + '\'' +
                "nome='" + nome + '\'' +
                "email='" + email + '\'' +
                "regiao='" + regiao + '\'' +
                "telefone='" + telefone + '\'' +
                '}';
    }
}
