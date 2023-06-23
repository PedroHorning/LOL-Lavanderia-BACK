package br.net.lol;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "roupas")
@RequestMapping("/api")
public class Roupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_roupa")
    private Integer id;
    @Column(name = "prazo")
    private Integer deadline;
    @Column(name = "descricao")
    private String name;
    @Column(name = "valor_unitario")
    private Double price;

    // getters e setters

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
