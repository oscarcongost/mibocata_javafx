module org.example.mibocatajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens org.example.mibocatajavafx to javafx.fxml;
    exports org.example.mibocatajavafx;
}