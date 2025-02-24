package org.example.mibocatajavafx.dao;

import jakarta.persistence.NoResultException;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.models.Pedido;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class PedidoDAO {
    public Pedido pedidoHoy(String alumnoMac) {
        LocalDate diaHoy = LocalDate.now();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from Pedido p where p.alumnoMac = :alumnoMac and p.fecha = :fecha");
            query.setParameter("alumnoMac", alumnoMac);
            query.setParameter("fecha", diaHoy);

            if (query.getSingleResult() != null) {
                return (Pedido) query.getSingleResult();
            }
        } catch (NoResultException e) {
            System.out.println("No se encontro el pedido");
        }
        return null;
    }

    public void actualizarPedido(Pedido pedido) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pedido);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public void save(Pedido pedido) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            if (pedido.getId() == null) {
                session.persist(pedido);
                session.flush(); //Forzar sincronización con la BD para obtener el ID
            } else {
                session.merge(pedido);
            }

            tx.commit();

            System.out.println("Pedido guardado con ID: " + pedido.getId());
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.out.println("Error al guardar el pedido");
        }
    }
    public List<Pedido> recogerPedidos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido", Pedido.class).list();
        }
    }

    public List<Pedido> recogerPedidosConBocadillos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT p FROM Pedido p JOIN FETCH p.bocadillo", Pedido.class).list();
        }
    }

    public List<Pedido> recogerPedidosPendientesHoy() {
        LocalDate diaHoy = LocalDate.now();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Pedido p WHERE p.fecha = :fecha AND p.retirado = false", Pedido.class);
            query.setParameter("fecha", diaHoy);

            return query.getResultList();
        }
    }

    public List<Pedido> recogerPedidosAlumno(String mac) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido p WHERE p.alumnoMac = :mac", Pedido.class)
                    .setParameter("mac", mac)
                    .list();
        }
    }

    public List<Pedido> recogerPedidosPendientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido p WHERE p.retirado = false", Pedido.class).list();
        }
    }
}

