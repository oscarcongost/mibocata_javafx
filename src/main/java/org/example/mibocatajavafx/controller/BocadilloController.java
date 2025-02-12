package org.example.mibocatajavafx.controller;

import org.example.mibocatajavafx.dao.BocadilloDAO;
import org.example.mibocatajavafx.models.Bocadillo;

import java.util.List;

public class BocadilloController {
    private final BocadilloDAO bocadilloDAO;

    public BocadilloController() {
        this.bocadilloDAO = new BocadilloDAO();
    }

    public List<Bocadillo> listarBocadillos() {
        return bocadilloDAO.obtenerTodosBocadillos();
    }

    public List<Bocadillo> listarBocadillosPorTipo(String tipo) {
        return bocadilloDAO.obtenerBocadillosPorTipo(tipo);
    }

}
