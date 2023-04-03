

module com.mycompany.miniprojet_glovo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.miniprojet_glovo to javafx.fxml;
    exports com.mycompany.miniprojet_glovo;
    
    exports com.mycompany.Model;
    exports com.mycompany.Controller;
    
    requires java.sql;
}
