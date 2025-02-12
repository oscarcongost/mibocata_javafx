module org.example.mibocatajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.mibocatajavafx to javafx.fxml;
    exports org.example.mibocatajavafx;
    exports org.example.mibocatajavafx.controller; // Exporta el paquete donde se encuentra LoginController
    opens org.example.mibocatajavafx.controller to javafx.fxml; // Asegúrate de que el controlador se pueda acceder desde FXML
    opens org.example.mibocatajavafx.models to org.hibernate.orm.core; // Abre el paquete models a Hibernate
}
