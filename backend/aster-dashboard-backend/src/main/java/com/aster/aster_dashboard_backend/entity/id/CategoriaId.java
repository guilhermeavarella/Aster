package com.aster.aster_dashboard_backend.entity.id;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CategoriaId implements Serializable{

    @Column(name="categoria")
    private String categoria;

    @Column(name="produto_id")
    private String produtoId;

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof CategoriaId)) return false;
        CategoriaId that=(CategoriaId) o;
        return Objects.equals(categoria, that.categoria) && Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(categoria, produtoId);
    }

    @Override
    public String toString(){
        return "CategoriaId{" +
                "categoria='" + categoria + '\'' +
                ", produtoId=" + produtoId +
                '}';
    }
}
