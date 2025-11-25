package com.aster.aster_dashboard_backend.entity.id;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProdutoVersaoId implements Serializable {

    @Column(name = "numero_versao")
    private String numeroVersao;

    @Column(name = "produto_id")
    private String produtoId;

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
