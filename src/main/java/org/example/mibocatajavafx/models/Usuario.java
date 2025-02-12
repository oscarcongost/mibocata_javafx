package org.example.mibocatajavafx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "mac", unique = true, length = 12)
    private String mac;

    @Column(name = "contraseña", length = 100)
    private String contrasena;

    @Column(name = "rol", length = 10)
    private String rol;

    public Usuario() {
    }

    public Usuario(String email, String mac, String contrasena, String rol) {
        this.email = email;
        this.mac = mac;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
