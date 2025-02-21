/*
package org.example.mibocatajavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Pedido;

import java.util.List;

public class CocinaController {

    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn<Pedido, Long> columnaId;
    @FXML
    private TableColumn<Pedido, String> columnaMac;
    @FXML
    private TableColumn<Pedido, String> columnaBocadillo;
    @FXML
    private TableColumn<Pedido, String> columnaFecha;
    @FXML
    private TableColumn<Pedido, String> columnaHora;
    @FXML
    private TableColumn<Pedido, Boolean> columnaRetirado;
    @FXML
    private TextField txtTotalCalientes;
   @FXML
   private TextField txtTotalFrios;

   // private PedidoDAO pedidoDAO = new PedidoDAO();

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaMac.setCellValueFactory(new PropertyValueFactory<>("alumnoMac"));
        columnaBocadillo.setCellValueFactory(new PropertyValueFactory<>("bocadilloNombre"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columnaRetirado.setCellFactory(tc -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setOnAction(event -> {
                    Pedido pedido = getTableView().getItems().get(getIndex());
                    pedido.setRetirado(checkBox.isSelected());
                    pedidoDAO.actualizarPedido(pedido);
                });
            }

            @Override
            protected void updateItem(Boolean retirado, boolean empty) {
                super.updateItem(retirado, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(retirado != null && retirado);
                    setGraphic(checkBox);
                }
            }
        });

        cargarPedidos();
    }


    private void cargarPedidos() {
        List<Pedido> listaPedidos = pedidoDAO.recogerPedidosPendientesHoy();
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList(listaPedidos);
        tablaPedidos.setItems(pedidos);

        long calientes = listaPedidos.stream()
                .filter(p -> p.getBocadillo() != null && "caliente".equalsIgnoreCase(p.getBocadillo().getTipo().name()))
                .count();

        long frios = listaPedidos.stream()
                .filter(p -> p.getBocadillo() != null && "frío".equalsIgnoreCase(p.getBocadillo().getTipo().name()))
                .count();

        txtTotalCalientes.setText(String.valueOf(calientes));
        txtTotalFrios.setText(String.valueOf(frios));
    }

    @FXML
    private void marcarComoRetirado() {
        Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            pedidoSeleccionado.setRetirado(true);
            pedidoDAO.actualizarPedido(pedidoSeleccionado);
            cargarPedidos();
        } else {
            mostrarAlerta("Error", "Selecciona un pedido para marcarlo como retirado.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

 */
