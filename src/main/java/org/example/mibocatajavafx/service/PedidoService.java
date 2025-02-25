package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Pedido;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoService {

    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public Pedido obtenerPedidoPorAlumno(String alumnoMac) {
        if (alumnoMac == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        return pedidoDAO.obtenerPedidoHoyPorAlumno(alumnoMac);
    }


    public void guardarPedido(Pedido pedido) {
        if (pedido.getFecha() == null || pedido.getAlumno() == null || pedido.getBocadillo() == null) {
            throw new IllegalArgumentException("El pedido no puede tener valores nulos");
        }
        pedidoDAO.guardarPedido(pedido);
    }

    public void actualizarPedido(Pedido pedidoActual) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(pedidoActual);
            tx.commit();
        }
    }
}
