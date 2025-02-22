package org.example.mibocatajavafx.models;

import jakarta.persistence.*;
import org.example.mibocatajavafx.utils.TipoBocadillo;

import java.util.Date;

@Entity
@Table(name = "bocadillo")
public class Bocadillo {

    @Id
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "ingredientes")
    private String ingredientes;

    @Column(name = "pvp", nullable = false)
    private double pvp;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoBocadillo tipo;

    @Column(name = "dia_venta", length = 9)
    private String diaVenta;

    public Bocadillo() {
    }

    public Bocadillo(String nombre, Date fechaBaja, String ingredientes, double pvp, TipoBocadillo tipo, String diaVenta) {
        this.nombre = nombre;
        this.fechaBaja = fechaBaja;
        this.ingredientes = ingredientes;
        this.pvp = pvp;
        this.tipo = tipo;
        this.diaVenta = diaVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public TipoBocadillo getTipo() {
        return tipo;
    }

    public void setTipo(TipoBocadillo tipo) {
        this.tipo = tipo;
    }

    public String getDiaVenta() {
        return diaVenta;
    }

    public void setDiaVenta(String diaVenta) {
        this.diaVenta = diaVenta;
    }

}

