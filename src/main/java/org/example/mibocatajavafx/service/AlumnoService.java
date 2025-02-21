package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.AlumnoDAO;
import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AlumnoService {
    private String rolUsuario; // Variable para guardar el rol del usuario
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public boolean validarCredenciales(String correoUsuario, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Buscar en la tabla Usuario
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

            // Si no es admin ni cocina, buscar en Alumno
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

            // Si no es usuario ni alumno, credenciales incorrectas
            System.out.println("Usuario no encontrado. Verifique sus credenciales.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // En caso de error en la base de datos
        }
    }

    public String conseguirAlumno(String correoAlumno) {
        if (correoAlumno.isBlank() || correoAlumno.isEmpty()) {
            throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        }

        return alumnoDAO.conseguirAlumno(correoAlumno);
    }

    public String getRolUsuario() {
        return rolUsuario;
    }


    public Alumnos conseguirAlumnoNombre(String nombreAlumno) {
        if (nombreAlumno.isBlank() || nombreAlumno.isEmpty()) {
            throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        }

        return alumnoDAO.conseguirAlumnoNombre(nombreAlumno);
    }
}
