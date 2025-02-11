package org.example.mibocatajavafx.dao;

import org.example.mibocatajavafx.models.Usuarios;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {

    public void guardarUsuario(Usuarios usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuarios usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void borrarUsuario(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Usuarios usuario = session.get(Usuarios.class, id);
            if (usuario != null) {
                session.remove(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Usuarios> listarUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Usuarios", Usuarios.class).list();
        }
    }

    public Usuarios buscarUsuario(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuarios.class, id);
        }
    }
}
