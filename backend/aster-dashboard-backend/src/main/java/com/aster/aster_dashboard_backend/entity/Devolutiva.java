package com.aster.aster_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="devolutiva")
public abstract class Devolutiva {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="data_envio")
    private LocalDate dataEnvio;

    @Column(name="assunto")
    private String assunto;

    @Column(name="mensagem")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name="cliente_documento")
    private Cliente cliente;

    @Override
    public String toString() {
        return "Devolutiva{" +
                "id='" + id + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", assunto='" + assunto + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", produtoId=" + produto.getId() +
                ", clienteDocumento=" + cliente.getDocumento() +
                '}';
    }
}

