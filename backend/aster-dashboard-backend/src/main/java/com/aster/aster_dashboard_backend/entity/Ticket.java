package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="devolutiva_id")
@Table(name="ticket")
public class Ticket extends Devolutiva {

    @Column(name="status")
    private String status;

    @Column(name="resposta")
    private String resposta;

    public Ticket(String id, LocalDate dataEnvio, String assunto, String mensagem, Produto produto, Cliente cliente, String status, String resposta) {
        super(id, dataEnvio, assunto, mensagem, produto, cliente);
        this.status = status;
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "status='" + status + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }
}
