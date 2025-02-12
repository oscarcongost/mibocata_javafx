package org.example.mibocatajavafx.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_mac", nullable = false, length = 12)
    private String alumnoMac;

    @Column(name = "bocadillo_nombre", nullable = false, length = 50)
    private String bocadilloNombre;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "hora", nullable = false)
    private Date hora;

    @Column(name = "retirado", nullable = false)
    private boolean retirado;

    public Pedido() {
    }

    public Pedido(Long id, String alumnoMac, String bocadilloNombre, Date fecha, Date hora, boolean retirado) {
        this.id = id;
        this.alumnoMac = alumnoMac;
        this.bocadilloNombre = bocadilloNombre;
        this.fecha = fecha;
        this.hora = hora;
        this.retirado = retirado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlumnoMac() {
        return alumnoMac;
    }

    public void setAlumnoMac(String alumnoMac) {
        this.alumnoMac = alumnoMac;
    }

    public String getBocadilloNombre() {
        return bocadilloNombre;
    }

    public void setBocadilloNombre(String bocadilloNombre) {
        this.bocadilloNombre = bocadilloNombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isRetirado() {
        return retirado;
    }

    public void setRetirado(boolean retirado) {
        this.retirado = retirado;
    }

}
