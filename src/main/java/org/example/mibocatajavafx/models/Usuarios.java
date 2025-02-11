package org.example.mibocatajavafx.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Usuarios")

public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "mac", nullable = false, unique = true)
    private String mac;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "rol", nullable = false)
    private String rol;

    public Usuarios() {
    }

    public Usuarios(Long id, String correo, String contrasena, String mac, Date fechaBaja, String rol) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.mac = mac;
        this.fechaBaja = fechaBaja;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
