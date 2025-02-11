package org.example.mibocatajavafx.controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class LoginController {
    public void start(Stage primaryStage) {
        // Cargar la fuente desde la carpeta resources/fonts/
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Regular.ttf"), 20);

        Label label = new Label("Texto con Google Font");
        label.setFont(customFont); // Aplicar la fuente

        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Ejemplo de Google Fonts en JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
