package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.models.Pedido;


public class PedidoService {

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public Pedido conseguirPedidoHoy(String alumnoMac){
        if(alumnoMac == null){
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        return pedidoDAO.pedidoHoy(alumnoMac);
    }

    public void save(Pedido pedido){
        if(pedido.getFecha() == null || pedido.getAlumnoMac() == null|| pedido.getBocadillo() == null){
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        pedidoDAO.save(pedido);
    }
}
