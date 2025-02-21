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

}
