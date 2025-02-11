module org.example.mibocatajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;


    opens org.example.mibocatajavafx to javafx.fxml;
    exports org.example.mibocatajavafx;
}