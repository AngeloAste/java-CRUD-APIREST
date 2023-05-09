package com.java.crudapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.java.crudapi.model.User;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date modified;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    public User() {}

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Getters and setters

    // ... (Los getters y setters no se modifican)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", created="
                + created + ", modified=" + modified + ", phones=" + phones + "]";
    }
}




