package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="produto")
public class Produto{

    @Id
    private String id;

    @Column(name="status")
    private String status;

    @Column(name="icone")
    private String icone;

    @Column(name="descricao_breve")
    private String descricaoBreve;

    @Column(name="nome")
    private String nome;

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", icone='" + icone + '\'' +
                ", descricaoBreve='" + descricaoBreve + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
