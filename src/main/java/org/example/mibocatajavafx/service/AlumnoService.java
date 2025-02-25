package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.AlumnoDAO;
import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.models.Alumnos;

public class AlumnoService {
    private String rolUsuario;
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public boolean autenticarCredenciales(String correoUsuario, String contrasena) {

        Usuario usuario = alumnoDAO.obtenerUsuarioPorCredenciales(correoUsuario, contrasena);
        if (usuario != null) {
            rolUsuario = usuario.getRol().toLowerCase();
            if (rolUsuario.equals("admin") || rolUsuario.equals("cocina")) {
                System.out.println("Bienvenido, " + rolUsuario);
                return true;
            }
        }

        Alumnos alumno = alumnoDAO.obtenerAlumnoPorNombre(correoUsuario, contrasena);
        if (alumno != null) {
            rolUsuario = "alumno";
            System.out.println("Bienvenido, Alumno.");
            return true;
        }

        System.out.println("Usuario no encontrado. Verifique sus credenciales.");
        return false;
    }

    public String obtenerAlumnoPorCorreo(String correoAlumno) {
        if (correoAlumno.isBlank() || correoAlumno.isEmpty()) {
            throw new IllegalArgumentException("El campo 'correo' es obligatorio");
        }
        return alumnoDAO.obtenerNombreAlumnoPorCorreo(correoAlumno);
    }

    public String obtenerRolUsuario() {
        return rolUsuario;
    }

    public Alumnos obtenerAlumnoPorNombre(String nombreAlumno) {
        if (nombreAlumno.isBlank() || nombreAlumno.isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombre' es obligatorio");
        }
        return alumnoDAO.obtenerAlumnoPorNombre(nombreAlumno);
    }
}
