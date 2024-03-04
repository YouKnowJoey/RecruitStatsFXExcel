module com.mycompany.liaisondash {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    
    opens com.mycompany.liaisondash to javafx.fxml;
    exports com.mycompany.liaisondash;
}
