package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="usuario")
public class Usuario {

    @Id
    @Column(name="chave_uso")
    private String chaveUso;

    @Override
    public String toString() {
        return "Usuario{" +
                "chaveUso='" + chaveUso + '\'' +
                '}';
    }
}
