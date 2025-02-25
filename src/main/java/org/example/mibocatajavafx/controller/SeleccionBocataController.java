package org.example.mibocatajavafx.controller;

import jakarta.persistence.NoResultException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mibocatajavafx.models.Alumnos;
import org.example.mibocatajavafx.models.Bocadillo;
import org.example.mibocatajavafx.models.Pedido;
import org.example.mibocatajavafx.service.AlumnoService;
import org.example.mibocatajavafx.service.BocataService;
import org.example.mibocatajavafx.service.PedidoService;
import org.example.mibocatajavafx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class SeleccionBocataController {

    private final BocataService bocataService = new BocataService();
    private final AlumnoService alumnoService = new AlumnoService();
    private final PedidoService pedidoService = new PedidoService();
    private String nombreAlumno;
    private Pedido pedidoActual;
    private Pedido pedidoExistente;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label nombreFrio, nombreCaliente;

    @FXML
    private Label ingredientesFrio, ingredientesCaliente;

    @FXML
    private Label precioFrio, precioCaliente;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private VBox vboxFrio, vboxCaliente;

    @FXML
    private Label bocadilloSeleccionadoLabel;

    @FXML
    private Button btnConfirmar;

    @FXML
    public void initialize() {
        if (nombreAlumno != null) {
            nombreLabel.setText(nombreAlumno);
        }
        mostrarBocadillosHoy();

        vboxFrio.setOnMouseClicked(mouseEvent -> seleccionarBocadillo("frio"));
        vboxCaliente.setOnMouseClicked(mouseEvent -> seleccionarBocadillo("caliente"));

        btnConfirmar.setOnMouseClicked(mouseEvent -> confirmarPedido());
    }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/loginScreen.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root, 1920, 1080));
            loginStage.setTitle("Iniciar Sesión");
            loginStage.setResizable(false); // Evita que se redimensione
            loginStage.show();

            Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seleccionarBocadillo(String tipo) {
        String nombreBocadillo = tipo.equals("frio") ? nombreFrio.getText() : nombreCaliente.getText();
        Bocadillo bocadillo = bocataService.obtenerBocadilloPorNombre(nombreBocadillo);
        double precio = bocadillo.getPvp();
        Alumnos alumno = alumnoService.obtenerAlumnoPorNombre(nombreAlumno);

        pedidoExistente = pedidoService.obtenerPedidoPorAlumno(alumno.getMac());

        if (pedidoExistente != null) {

            if (!pedidoExistente.getBocadillo().getNombre().equals(bocadillo.getNombre())) {

                pedidoExistente.setBocadillo(bocadillo);
                pedidoExistente.setFecha(LocalDate.now());
                pedidoExistente.setHora(LocalTime.now());

                pedidoService.actualizarPedido(pedidoExistente);
                pedidoActual = pedidoExistente;
                mostrarMensajeAlerta("Pedido actualizado", "Tu pedido ha sido modificado.", Alert.AlertType.CONFIRMATION);
            }
        } else {
            pedidoActual = new Pedido(alumno, bocadillo, LocalDate.now(), LocalTime.now(), false);
            pedidoService.guardarPedido(pedidoActual);
            mostrarMensajeAlerta("Pedido creado", "Tu nuevo pedido ha sido registrado.", Alert.AlertType.CONFIRMATION);
        }

        bocadilloSeleccionadoLabel.setText("Bocadillo Seleccionado: " + nombreBocadillo + " | " + precio + " €");
    }

    public void establecerNombreUsuario(String nombreUsuario) {
        this.nombreAlumno = nombreUsuario;
        if (nombreLabel != null) {
            nombreLabel.setText(nombreUsuario);
        }
    }

    public List<Bocadillo> listarBocadillosHoy() {
        String diaHoy = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Bocadillo> query = session.createQuery("from Bocadillo b where b.diaVenta = :nombreHoy", Bocadillo.class);
            query.setParameter("nombreHoy", diaHoy);

            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("No se encontraron bocadillos para hoy.");
            return List.of();
        }
    }

    public void mostrarBocadillosHoy() {
        List<Bocadillo> bocadillos = listarBocadillosHoy();

        Bocadillo bocadilloFrio = null;
        Bocadillo bocadilloCaliente = null;

        for (Bocadillo b : bocadillos) {
            if (b.getTipo().name().equalsIgnoreCase("FRIO") && bocadilloFrio == null) {
                bocadilloFrio = b;
            } else if (b.getTipo().name().equalsIgnoreCase("CALIENTE") && bocadilloCaliente == null) {
                bocadilloCaliente = b;
            }
        }

        if (bocadilloFrio != null) {
            nombreFrio.setText(bocadilloFrio.getNombre());
            ingredientesFrio.setText(bocadilloFrio.getIngredientes());
            precioFrio.setText(String.format("%.2f €", bocadilloFrio.getPvp()));
        } else {
            nombreFrio.setText("No disponible");
            ingredientesFrio.setText("-");
            precioFrio.setText("-");
        }

        if (bocadilloCaliente != null) {
            nombreCaliente.setText(bocadilloCaliente.getNombre());
            ingredientesCaliente.setText(bocadilloCaliente.getIngredientes());
            precioCaliente.setText(String.format("%.2f €", bocadilloCaliente.getPvp()));
        } else {
            nombreCaliente.setText("No disponible");
            ingredientesCaliente.setText("-");
            precioCaliente.setText("-");
        }
    }

    private void confirmarPedido() {
        if (pedidoActual == null) {
            mostrarMensajeAlerta("Error", "No hay bocadillo seleccionado.", Alert.AlertType.WARNING);
            return;
        }

        if (pedidoExistente != null) {
            mostrarMensajeAlerta("Pedido ya existente", "Ya tienes un pedido para hoy. Solo puedes modificarlo.", Alert.AlertType.WARNING);
            return;
        }

        pedidoService.guardarPedido(pedidoActual);
        mostrarMensajeAlerta("Pedido creado", "Tu pedido ha sido registrado con éxito.", Alert.AlertType.CONFIRMATION);
        System.out.println("Pedido creado con ID: " + pedidoActual.getId());
    }

    private void mostrarMensajeAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
