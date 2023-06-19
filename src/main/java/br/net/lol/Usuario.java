package br.net.lol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	 @Id
	    @Column(name = "cpf")
	    private String cpf;

	    @Column(name = "nome")
	    private String nome;

	    @Column(name = "email")
	    private String email;

	    @Column(name = "senha")
	    private String senha;

	    @Column(name = "cep")
	    private String cep;

	    @Column(name = "telefone")
	    private String telefone;

	    @Column(name = "tipo")
	    private String tipo;

	    public String getCpf() {
	        return cpf;
	    }

	    public void setCpf(String cpf) {
	        this.cpf = cpf;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public String getCep() {
	        return cep;
	    }

	    public void setCep(String cep) {
	        this.cep = cep;
	    }

	    public String getTelefone() {
	        return telefone;
	    }

	    public void setTelefone(String telefone) {
	        this.telefone = telefone;
	    }

	    public String getTipo() {
	        return tipo;
	    }

	    public void setTipo(String tipo) {
	        this.tipo = tipo;
	    }
}

