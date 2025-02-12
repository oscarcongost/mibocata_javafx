package org.example.mibocatajavafx.controller;

import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Pedido;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;

    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = new PedidoDAO();
    }

    public void hacerPedido(String alumnoMAC, String bocadilloNombre) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setAlumnoMac(alumnoMAC);
        nuevoPedido.setBocadilloNombre(bocadilloNombre);
        nuevoPedido.setFecha(LocalDate.now());
        nuevoPedido.setHora(LocalTime.now());
        nuevoPedido.setRetirado(false);

        pedidoDAO.guardarPedido(nuevoPedido);
        System.out.println("Pedido realizado con éxito");
    }

    public List<Pedido> obtenerPedidosAlumno(String alumnoMAC) {
        return pedidoDAO.recogerPedidosAlumno(alumnoMAC);
    }

    public List<Pedido> obtenerPedidosPendientes() {
        return pedidoDAO.recogerPedidosPendientes();
    }

    public void retirarPedido(Long idPedido) {
        pedidoDAO.marcarPedidoRetirado(idPedido);
        System.out.println("Pedido marcado como retirado.");
    }
}
