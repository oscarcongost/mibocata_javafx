package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Pedido;

public class PedidoService {

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public Pedido conseguirPedidoHoy(String alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        return pedidoDAO.pedidoHoy(alumno);
    }

    public void save(Pedido pedido) {
        if (pedido.getFecha() == null || pedido.getAlumno() == null || pedido.getBocadillo() == null) {
            throw new IllegalArgumentException("El pedido no puede tener valores nulos");
        }
        pedidoDAO.save(pedido);
    }
}
