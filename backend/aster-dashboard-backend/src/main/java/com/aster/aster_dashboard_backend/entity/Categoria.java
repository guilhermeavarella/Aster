package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.CategoriaId;
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
@Table(name="categoria")
public class Categoria{

    @EmbeddedId
    private CategoriaId id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name="produto_id")
    private Produto produto;

    @Override
    public String toString() {
        return "Categoria{" +
                "categoria=" + id.getCategoria() +
                ", produtoId=" + id.getProdutoId() +
                '}';
    }
}
