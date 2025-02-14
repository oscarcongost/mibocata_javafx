package org.example.mibocatajavafx.dao;

import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AlumnoDAO {

    public void guardarAlumno(Alumnos alumno) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void actualizarAlumno(Alumnos alumno) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(alumno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void borrarAlumno(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Alumnos alumno = session.get(Alumnos.class, id);
            if (alumno != null) {
                session.remove(alumno);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Alumnos> listarAlumnos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Alumnos", Alumnos.class).list();
        }
    }

    public Alumnos buscarAlumno(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Alumnos.class, id);
        }
    }

}
