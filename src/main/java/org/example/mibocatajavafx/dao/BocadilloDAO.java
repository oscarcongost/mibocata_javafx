package org.example.mibocatajavafx.dao;

import org.example.mibocatajavafx.models.Bocadillo;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class BocadilloDAO {

    public Bocadillo obtenerBocadilloPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Bocadillo where nombre = :nombre");
            query.setParameter("nombre", nombre);
            return (Bocadillo) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error al obtener el nombre de la bocadillo");
        }
        return null;
    }

}
