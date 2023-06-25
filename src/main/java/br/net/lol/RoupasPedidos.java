package br.net.lol;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "roupas_pedido")
@RequestMapping("/api")
public class RoupasPedidos {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer roupas_pedido_id;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedidogeral_id")
    private Integer pedidoid;
    
    @Column(name = "roupa_id")
    @JsonProperty("id")
    private Integer roupa_id;
    
    @Column(name = "quantidade")
    @JsonProperty("quantity")
    private Integer quantidade;
    
    @Column(name = "valor_total")
    @JsonProperty("totalPrice")
    private Double valor_total;
    
    @Column(name = "deadline_total")
    private Integer deadline_total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roupa_id", referencedColumnName = "cod_roupa", insertable = false, updatable = false)
    private Roupa roupa;

    @Transient
    private String name;
    
    @PostLoad
    private void onLoad() {
        if (roupa != null) {
            this.name = roupa.getName();
            this.deadline = roupa.getDeadline();
            this.price = roupa.getPrice();
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Transient
    private Integer deadline;
    
    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }
    
    @Transient
    private Double price;
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getRoupas_pedido_id() {
        return roupas_pedido_id;
    }

    public void setRoupas_pedido_id(Integer roupas_pedido_id) {
        this.roupas_pedido_id = roupas_pedido_id;
    }

    public Integer getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(Integer pedidoid) {
        this.pedidoid = pedidoid;
    }

    @JsonProperty("id")
    public Integer getRoupa_id() {
        return roupa_id;
    }

    @JsonProperty("id")
    public void setRoupa_id(Integer roupa_id) {
        this.roupa_id = roupa_id;
    }

    @JsonProperty("quantity")
    public Integer getQuantidade() {
        return quantidade;
    }

    @JsonProperty("quantity")
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @JsonProperty("totalPrice")
    public Double getValor_total() {
        return valor_total;
    }

    @JsonProperty("totalPrice")
    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public Integer getDeadline_total() {
        return deadline_total;
    }

    public void setDeadline_total(Integer deadline_total) {
        this.deadline_total = deadline_total;
    }
}