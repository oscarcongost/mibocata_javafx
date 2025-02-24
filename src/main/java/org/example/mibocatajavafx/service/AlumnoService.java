package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.AlumnoDAO;
import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.models.Alumnos;

public class AlumnoService {
    private String rolUsuario;
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public boolean validarCredenciales(String correoUsuario, String contrasena) {

        Usuario usuario = alumnoDAO.buscarUsuario(correoUsuario, contrasena);
        if (usuario != null) {
            rolUsuario = usuario.getRol().toLowerCase();
            if (rolUsuario.equals("admin") || rolUsuario.equals("cocina")) {
                System.out.println("Bienvenido, " + rolUsuario);
                return true;
            }
        }

        Alumnos alumno = alumnoDAO.buscarAlumno(correoUsuario, contrasena);
        if (alumno != null) {
            rolUsuario = "alumno";
            System.out.println("Bienvenido, Alumno.");
            return true;
        }

        System.out.println("Usuario no encontrado. Verifique sus credenciales.");
        return false;
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
