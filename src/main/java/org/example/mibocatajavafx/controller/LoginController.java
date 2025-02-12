package org.example.mibocatajavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.mibocatajavafx.HelloApplication;
import org.example.mibocatajavafx.service.AlumnoService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private AlumnoService alumnoService = new AlumnoService();

    public LoginController() {
        System.out.println("Constructor de LoginController");
    }

    @FXML
    public void initialize() {
        System.out.println("initialize() llamado: emailField es " + emailField);
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String contrasena = passwordField.getText();

        FXMLLoader loader;
        Parent root;

        if (alumnoService.validarCredenciales(email, contrasena)) {
            // Obtener el rol del usuario
            String rol = alumnoService.getRolUsuario();


            // Decidir a qué página redirigir dependiendo del rol
            switch (rol) {
                case "admin":
                    loader = new FXMLLoader(HelloApplication.class.getResource("/screens/adminPage.fxml"));
                    break;
                case "cocina":
                    loader = new FXMLLoader(HelloApplication.class.getResource("/screens/cocinaPage.fxml"));
                    break;
                case "alumno":
                    loader = new FXMLLoader(HelloApplication.class.getResource("/screens/seleccionBocata.fxml"));
                    break;
                default:
                    System.out.println("Rol desconocido.");
                    return;
            }

            // Cargar la página correspondiente
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show(); // Muestra la nueva ventana
                // Cerrar la ventana de login
                Stage loginStage = (Stage) emailField.getScene().getWindow();
                loginStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error de inicio de sesión", "Correo o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
