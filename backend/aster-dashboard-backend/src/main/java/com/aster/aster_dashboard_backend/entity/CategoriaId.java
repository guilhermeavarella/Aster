
package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
public class CategoriaId implements Serializable{

    @Column(name="categoria", length=30, nullable=false)
    private String categoria;

    @Column(name="produto_id", nullable=false)
    private Integer produtoId;

   public ProdutoVersaoId(String numeroVersao, Integer produtoId){
        this.numeroVersao = numeroVersao;
        this.produtoId = produtoId;
    }

    public String getCategoria(){
        return categoria;
    }

    public Integer getProdutoId(){
        return produtoId;
    }

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
