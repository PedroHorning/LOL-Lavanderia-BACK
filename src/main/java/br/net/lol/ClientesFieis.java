package br.net.lol;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes_fieis")
@RequestMapping("/api")

public class ClientesFieis {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId;
    
    @Column(name = "quantidade_total")
    private Integer quantidade_total;
    
    @Column(name = "valor_total")
    private Integer valor_total;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuario;

    @Transient
    private String name;
    
    @Transient
    private String email;
    
    @PostLoad
    private void onLoad() {
        if (usuario != null) {
            this.name = usuario.getName();
            this.email = usuario.getEmail();
        }
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public Integer getQuantidade_total() {
        return quantidade_total;
    }

    public Integer getValor_total() {
        return valor_total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setQuantidade_total(Integer quantidade_total) {
        this.quantidade_total = quantidade_total;
    }

    public void setValor_total(Integer valor_total) {
        this.valor_total = valor_total;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
