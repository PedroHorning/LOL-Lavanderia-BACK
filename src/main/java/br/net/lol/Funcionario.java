package br.net.lol;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "funcionario")
@RequestMapping("/api")
public class Funcionario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
    @Column(name = "nome")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "senha")
    private String password;
    
    @Column(name = "datanasc")
    @JsonProperty("birth_date")
    private LocalDate birth_date;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("birth_date")
    public LocalDate getBirthDate() {
        return birth_date;
    }

    @JsonProperty("birth_date")
    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
}
