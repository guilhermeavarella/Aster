package com.aster.aster_dashboard_backend.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContemId implements Serializable {

    @Column(name="produto_id")
    private String produtoId;

    @Column(name="pacote_nome")
    private String pacoteNome;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContemId contemId = (ContemId) o;
        return Objects.equals(getProdutoId(), contemId.getProdutoId()) && Objects.equals(getPacoteNome(), contemId.getPacoteNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProdutoId(), getPacoteNome());
    }
}
