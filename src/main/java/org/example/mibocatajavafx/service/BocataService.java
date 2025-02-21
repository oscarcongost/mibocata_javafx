package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.BocataDAO;
import org.example.mibocatajavafx.models.Bocadillo;

public class BocataService {
    private final BocataDAO bocataDAO = new BocataDAO();

    public Bocadillo getBocadilloNombre(String nombre) {
        if(!nombre.isEmpty() || !nombre.isBlank()) {
            return bocataDAO.getBocadilloNombre(nombre);
        }
        return null;
    }


}
