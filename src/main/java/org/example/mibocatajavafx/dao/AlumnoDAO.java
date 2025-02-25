package org.example.mibocatajavafx.dao;

import jakarta.persistence.NoResultException;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AlumnoDAO {

    public String obtenerNombreAlumnoPorCorreo(String correo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Alumnos where correo = :correo");
            query.setParameter("correo", correo);
            Alumnos alumnos = (Alumnos) query.uniqueResult();
            return alumnos.getNombre();
        } catch (Exception e) {
            return null;
        }
    }

    public Alumnos obtenerAlumnoPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Alumnos a where a.nombre = :nombre");
            query.setParameter("nombre", nombre);
            return (Alumnos) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No se encontró el alumno");
            return null;
        }
    }

    public Usuario obtenerUsuarioPorCredenciales(String email, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery(
                    "FROM Usuario WHERE email = :email AND contrasena = :contrasena", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("contrasena", contrasena);
            return query.uniqueResult();
        }
    }

    public Alumnos obtenerAlumnoPorNombre(String correo, String pass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Alumnos> query = session.createQuery(
                    "FROM Alumnos WHERE correo = :correo AND pass = :pass", Alumnos.class);
            query.setParameter("correo", correo);
            query.setParameter("pass", pass);
            return query.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
