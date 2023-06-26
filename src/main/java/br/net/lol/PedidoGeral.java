package br.net.lol;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pedidos_geral")
@RequestMapping("/api")
public class PedidoGeral {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pedido")
    private Integer id;
	
    @Column(name = "status")
    private String status;
    
    @Column(name = "usuario_id")
    @JsonProperty("usuarioId")
    private Integer idUsuario;
    
    @Column(name = "data")
    private LocalDate data;
    
    @Column(name = "preco_total")
    private Double price;
    
    @Column(name = "deadline")
    private Integer deadline;
    
    @OneToMany(mappedBy = "pedidoid", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoupasPedidos> roupas;
    
    public List<RoupasPedidos> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<RoupasPedidos> roupas) {
        this.roupas = roupas;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonProperty("usuarioId")
    public Integer getidUsuario() {
        return idUsuario;
    }

    @JsonProperty("usuarioId")
    public void setidUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    } 
}