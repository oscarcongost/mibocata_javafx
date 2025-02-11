package org.example.mibocatajavafx.service;

import org.example.mibocatajavafx.dao.AlumnoDAO;
import org.example.mibocatajavafx.models.Usuario;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AlumnoService {
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private List<Usuario> listadoUsuarios = new ArrayList<>();

    public boolean validarCredenciales (String correoUsuario, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            listadoUsuarios = session.createQuery("from Usuario").getResultList();
        }
        for (Usuario usuario : listadoUsuarios) {
            if (usuario.getEmail().equals(correoUsuario) && usuario.getContrasena().equals(contrasena)){
                return true;
            }
        }
    }
}
