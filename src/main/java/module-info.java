module org.example.mibocatajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.mibocatajavafx to javafx.fxml;
    exports org.example.mibocatajavafx;
}