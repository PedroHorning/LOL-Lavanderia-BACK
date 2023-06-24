package br.net.lol;

import javax.persistence.*;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;

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
    private Integer id_usuario;
    
    @Column(name = "data")
    private LocalDate data;
    
    @Column(name = "preco_total")
    private Double price;
    
    @Column(name = "deadline")
    private Integer deadline;
    
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
    
    public Integer getUsuario_Id() {
        return id_usuario;
    }

    public void setUsuario_Id(Integer id_usuario) {
        this.id_usuario = id_usuario;
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
