package org.example.mibocatajavafx.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Alumno")
public class Alumnos {

    @Id
    @Column(name = "mac", nullable = false, unique = true)
    private String mac;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "motivo_baja")
    private String motivo_baja;
    @Column(name = "fecha_baja")
    private Date fecha_baja;
    @Column(name = "abonado", nullable = false)
    private int abonado;
    @Column(name = "pass", nullable = false)
    private String pass;
    @Column(name = "correo")
    private String correo;

    public Alumnos() {
    }

    public Alumnos(String mac, String nombre, String motivo_baja, Date fecha_baja, int abonado, String pass, String correo) {
        this.mac = mac;
        this.nombre = nombre;
        this.motivo_baja = motivo_baja;
        this.fecha_baja = fecha_baja;
        this.abonado = abonado;
        this.pass = pass;
        this.correo = correo;
    }



    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo_baja() {
        return motivo_baja;
    }

    public void setMotivo_baja(String motivo_baja) {
        this.motivo_baja = motivo_baja;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public int getAbonado() {
        return abonado;
    }

    public void setAbonado(int abonado) {
        this.abonado = abonado;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}