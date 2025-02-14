package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AlumnoService {
    private String rolUsuario; // Variable para guardar el rol del usuario

    public boolean validarCredenciales(String correoUsuario, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // busca en la tabla usuario
            Query<Usuario> queryUsuario = session.createQuery(
                    "FROM Usuario WHERE email = :email AND contrasena = :contrasena", Usuario.class);
            queryUsuario.setParameter("email", correoUsuario);
            queryUsuario.setParameter("contrasena", contrasena);

            Usuario usuario = queryUsuario.uniqueResult();
            if (usuario != null) {
                rolUsuario = usuario.getRol().toLowerCase();
                if (rolUsuario.equals("admin") || rolUsuario.equals("cocina")) {
                    System.out.println("Bienvenido, " + rolUsuario);
                    return true; // Usuario válido
                }
            }

            Query<Alumnos> queryAlumno = session.createQuery(
                    "FROM Alumnos WHERE correo = :correo AND pass = :pass", Alumnos.class);
            queryAlumno.setParameter("correo", correoUsuario);
            queryAlumno.setParameter("pass", contrasena);

            System.out.println(queryAlumno.uniqueResult());

            Alumnos alumno = queryAlumno.uniqueResult();
            if (alumno != null) {
                rolUsuario = "alumno";
                System.out.println("Bienvenido, Alumno.");
                return true; // Alumno válido
            }

            System.out.println("Usuario no encontrado. Verifique sus credenciales.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getRolUsuario() {
        return rolUsuario;
    }
}
