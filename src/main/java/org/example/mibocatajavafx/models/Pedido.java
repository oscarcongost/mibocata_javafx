package org.example.mibocatajavafx.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_mac", nullable = false, length = 12)
    private String alumnoMac;


    @ManyToOne
    @JoinColumn(name = "bocadillo_nombre", referencedColumnName = "nombre")
    private Bocadillo bocadillo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "retirado", nullable = false)
    private boolean retirado;

    public Pedido() {
    }

    public Pedido(String alumnoMac, Bocadillo bocadillo, LocalDate fecha, LocalTime hora, boolean retirado) {
        this.alumnoMac = alumnoMac;
        this.bocadillo = bocadillo;
        this.fecha = fecha;
        this.hora = hora;
        this.retirado = retirado;
    }

    public Pedido(Long id, String alumnoMac, Bocadillo bocadillo, LocalDate fecha, LocalTime hora, boolean retirado) {
        this.id = id;
        this.alumnoMac = alumnoMac;
        this.bocadillo = bocadillo;
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



    public Bocadillo getBocadillo() {
        return bocadillo;
    }

    public void setBocadillo(Bocadillo bocadillo) {
        this.bocadillo = bocadillo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
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