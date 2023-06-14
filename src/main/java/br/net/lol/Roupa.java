package br.net.lol;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "roupas")
@RequestMapping("/api")
public class Roupa {
    @Id
    @Column(name = "cod_roupa")
    private Integer codigo;

    private String prazo;
    private String descricao;
    @Column(name = "valor_unitario")
    private Double valorUnitario;

    // getters e setters

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
