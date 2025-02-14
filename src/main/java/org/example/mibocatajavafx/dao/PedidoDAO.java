package org.example.mibocatajavafx.dao;

import jakarta.persistence.Query;
import org.example.mibocatajavafx.models.Pedido;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;

public class PedidoDAO {

    public void guardarPedido(Pedido pedido) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(pedido);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
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

    public void marcarPedidoRetirado(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pedido pedido = session.get(Pedido.class, id);
            if (pedido != null) {
                pedido.setRetirado(true);
                session.merge(pedido);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
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