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
@Table(name="devolutiva")
public class Devolutiva{

    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="data_envio")
    private LocalDate dataEnvio;

    @Column(name="assunto", length=50, nullable=false)
    private String assunto;

    @Column(name="mensagem", length=500)
    private String mensagem;

    @Column(name="produto_id", nullable=false)
    private Integer produtoId;

    @Column(name="documento", nullable=false)
    private String documento;
    
    @Override
    public String toString(){
        return "Produto{" +
                "id='" + id + '\'' +
                ", data_envio='" + dataEnvio + '\'' +
                ", assunto='" + assunto + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", produto_id='" + produtoId + '\'' +
                ", documento='" + documento + '\'' +
                '}';
    }
}
