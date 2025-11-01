package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
public class ProdutoVersaoId implements Serializable {

    @Column(name = "numero_versao", length = 10, nullable = false)
    private String numeroVersao;

    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;

   public ProdutoVersaoId(String numeroVersao, Integer produtoId) {
        this.numeroVersao = numeroVersao;
        this.produtoId = produtoId;
    }

    public String getNumeroVersao() {
        return numeroVersao;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoVersaoId)) return false;
        ProdutoVersaoId that = (ProdutoVersaoId) o;
        return numeroVersao.equals(that.numeroVersao) && produtoId.equals(that.produtoId);
    }

    @Override
    public int hashCode() {
        return numeroVersao.hashCode() + produtoId.hashCode();
    }

    @Override
    public String toString() {
        return "ProdutoVersaoId{" +
                "numeroVersao='" + numeroVersao + '\'' +
                ", produtoId=" + produtoId +
                '}';
    }
}
