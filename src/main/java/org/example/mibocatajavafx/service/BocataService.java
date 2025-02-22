package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.BocadilloDAO;
import org.example.mibocatajavafx.models.Bocadillo;

public class BocataService {
    private final BocadilloDAO bocataDAO = new BocadilloDAO();

    public Bocadillo getBocadilloNombre(String nombre) {
        if (!nombre.isEmpty() || !nombre.isBlank()) {
            return bocataDAO.getBocadilloNombre(nombre);
        }
        return null;
    }

}
