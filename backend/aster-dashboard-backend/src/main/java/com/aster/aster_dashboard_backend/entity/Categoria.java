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
@Table(name="categoria")
public class Categoria{

    @EmbeddedId
    private CategoriaId id;

    @MapsId("produtoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Override
    public String toString() {
        return "Categoria{" +
                "categoria='" + id.getCategoria + '\'' +
                ", produtoId='" + id.getProdutoId + '\'' +
                '}';
    }
}
