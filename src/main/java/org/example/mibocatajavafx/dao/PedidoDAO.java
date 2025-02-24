
package org.example.mibocatajavafx.dao;

import jakarta.persistence.NoResultException;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pedido> query = session.createQuery("from Pedido p where p.alumno.mac = :alumnoMac and p.fecha = :fecha", Pedido.class);
            query.setParameter("alumnoMac", alumnoMac);
            query.setParameter("fecha", diaHoy);

            return query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Error al consultar el pedido.");
            return null;
        }
    }


    public void save(Pedido pedido) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            if (pedido.getId() == null) {
                session.persist(pedido);
                session.flush();
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

    public List<Pedido> recogerPedidosPendientes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pedido> query = session.createQuery("FROM Pedido p WHERE p.retirado = false ", Pedido.class);
            return query.getResultList();

        }
    }
}