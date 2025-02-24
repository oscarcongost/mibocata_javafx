package org.example.mibocatajavafx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mibocatajavafx.dao.PedidoDAO;
import org.example.mibocatajavafx.models.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CocinaController {

    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn<Pedido, Long> columnaId;
    @FXML
    private TableColumn<Pedido, String> columnaAlumno;
    @FXML
    private TableColumn<Pedido, String> columnaBocadillo;
    @FXML
    private TableColumn<Pedido, String> columnaFecha;
    @FXML
    private TableColumn<Pedido, String> columnaHora;
    @FXML
    private TableColumn<Pedido, String> columnaTipo;
    @FXML
    private TableColumn<Pedido, Boolean> columnaRetirado;
    @FXML
    private Label labelTotalCalientes;
    @FXML
    private Label labelTotalFrios;
    @FXML
    private DatePicker datePickerFiltro;
    @FXML
    private TextField textFieldAlumno;
    @FXML
    private ComboBox<String> comboBoxTipo;
    @FXML
    private Button btnFiltrar;
    @FXML
    private TextField txtPagina;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;

    private LocalDate fechaSeleccionada = LocalDate.now();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private List<Pedido> listaPedidos;
    private static final int REGISTROS_POR_PAGINA = 10;
    private ObservableList<Pedido> pedidosPaginados = FXCollections.observableArrayList();
    private int totalPaginas = 1;

    @FXML
    public void initialize() {
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaAlumno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlumno().getNombre()));
        columnaBocadillo.setCellValueFactory(cellData -> {
            Pedido pedido = cellData.getValue();
            if (pedido.getBocadillo() != null) {
                return new SimpleStringProperty(pedido.getBocadillo().getNombre());
            } else {
                return new SimpleStringProperty("Sin bocadillo");
            }
        });
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        columnaTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBocadillo().getTipo().toString()));

        columnaRetirado.setCellFactory(tc -> new TableCell<Pedido, Boolean>() {
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

        comboBoxTipo.getItems().addAll("caliente", "frio", "");

        btnFiltrar.setOnAction(event -> {
            System.out.println("✔ Botón Filtrar presionado");
            aplicarFiltro();
        });
        btnAnterior.setDisable(true);

        datePickerFiltro.setValue(LocalDate.now());

        cargarPedidos();
    }

    private void cargarPedidos() {
        listaPedidos = pedidoDAO.recogerPedidosPendientes();
        aplicarFiltro();
    }

    @FXML
    private void aplicarFiltro() {
        String nombreAlumno = textFieldAlumno.getText().toLowerCase().trim();
        String tipoSeleccionado = comboBoxTipo.getSelectionModel().getSelectedItem();
        LocalDate fechaSeleccionada = datePickerFiltro.getValue();

        List<Pedido> pedidosFiltrados = listaPedidos.stream()
                // Filtro por nombre del alumno
                .filter(p -> nombreAlumno.isEmpty() || p.getAlumno().getNombre().toLowerCase().contains(nombreAlumno))
                // Filtro por tipo de bocadillo
                .filter(p -> tipoSeleccionado == null || tipoSeleccionado.isEmpty() ||
                        (p.getBocadillo() != null && p.getBocadillo().getTipo().name().equalsIgnoreCase(tipoSeleccionado)))
                // Filtro por fecha (compara solo si la fecha no es null)
                .filter(p -> p.getFecha() != null && p.getFecha().equals(fechaSeleccionada))
                .collect(Collectors.toList());

        actualizarTotales(pedidosFiltrados);
        configurarPaginacion(pedidosFiltrados);
    }

    private void configurarPaginacion(List<Pedido> pedidos) {
        totalPaginas = (int) Math.ceil((double) pedidos.size() / REGISTROS_POR_PAGINA);
        totalPaginas = Math.max(totalPaginas, 1); // Asegurar al menos 1 página
        pedidosPaginados.setAll(pedidos);
        txtPagina.setText("1");
        actualizarTabla(0);
        btnAnterior.setDisable(true);
        btnSiguiente.setDisable(totalPaginas <= 1);
    }

    private void actualizarTabla(int paginaIndex) {
        int inicio = paginaIndex * REGISTROS_POR_PAGINA;
        int fin = Math.min(inicio + REGISTROS_POR_PAGINA, pedidosPaginados.size());
        tablaPedidos.setItems(FXCollections.observableArrayList(pedidosPaginados.subList(inicio, fin)));

        txtPagina.setText(String.valueOf(paginaIndex + 1)); // Mostrar en la UI la página actual
        btnAnterior.setDisable(paginaIndex == 0);
        btnSiguiente.setDisable(paginaIndex >= totalPaginas - 1);

        tablaPedidos.refresh();
    }

    private void actualizarTotales(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            labelTotalCalientes.setText("0");
            labelTotalFrios.setText("0");
            return;
        }

        long calientes = pedidos.stream()
                .filter(p -> p.getBocadillo() != null
                        && p.getBocadillo().getNombre() != null
                        && p.getBocadillo().getTipo() != null
                        && "caliente".equalsIgnoreCase(p.getBocadillo().getTipo().name()))
                .count();

        long frios = pedidos.stream()
                .filter(p -> p.getBocadillo() != null
                        && p.getBocadillo().getNombre() != null
                        && p.getBocadillo().getTipo() != null
                        && "frio".equalsIgnoreCase(p.getBocadillo().getTipo().name()))
                .count();

        labelTotalCalientes.setText(String.valueOf(calientes));
        labelTotalFrios.setText(String.valueOf(frios));
    }

    @FXML
    public void anteriorPagina(ActionEvent actionEvent) {
        int paginaActual = Integer.parseInt(txtPagina.getText());
        if (paginaActual > 1) {
            paginaActual--;
            actualizarTabla(paginaActual - 1);
        }
    }

    @FXML
    public void siguientePagina(ActionEvent actionEvent) {
        int paginaActual = Integer.parseInt(txtPagina.getText());
        if (paginaActual < totalPaginas) {
            paginaActual++;
            actualizarTabla(paginaActual - 1);
        }
    }
}
