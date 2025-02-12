package org.example.mibocatajavafx.dao;

import org.example.mibocatajavafx.models.Bocadillo;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BocadilloDAO {

    public void guardarBocadillo(Bocadillo bocadillo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(bocadillo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void actualizarBocadillo(Bocadillo bocadillo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(bocadillo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void eliminarBocadillo(String nombre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Bocadillo bocadillo = session.get(Bocadillo.class, nombre);
            if (bocadillo != null) {
                transaction = session.beginTransaction();
                session.remove(bocadillo);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Bocadillo obtenerBocadillo(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bocadillo.class, nombre);
        }
    }

    public List<Bocadillo> obtenerTodosBocadillos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bocadillo", Bocadillo.class).list();
        }
    }

    public List<Bocadillo> obtenerBocadillosPorTipo(String tipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bocadillo b WHERE b.tipo = :tipo", Bocadillo.class)
                    .setParameter("tipo", tipo)
                    .list();
        }
    }

}
