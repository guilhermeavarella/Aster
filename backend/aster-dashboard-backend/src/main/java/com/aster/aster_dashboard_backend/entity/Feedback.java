package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="devolutiva_id")
@Table(name="feedback")
public class Feedback extends Devolutiva {

    @Column(name="avaliacao", precision=2, scale=1)
    private BigDecimal avaliacao;

    @Column(name="atividade_uso")
    private String atividadeUso;

    public Feedback(String id, LocalDate dataEnvio, String assunto, String mensagem, Produto produto, Cliente cliente, BigDecimal avaliacao, String atividadeUso) {
        super(id, dataEnvio, assunto, mensagem, produto, cliente);
        this.avaliacao = avaliacao;
        this.atividadeUso = atividadeUso;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "avaliacao=" + avaliacao +
                ", atividadeUso='" + atividadeUso + '\'' +
                '}';
    }
}
