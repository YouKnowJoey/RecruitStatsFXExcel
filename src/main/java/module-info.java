module com.mycompany.liaisondash {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;
    requires java.desktop;
    
    opens com.mycompany.liaisondash to javafx.fxml;
    exports com.mycompany.liaisondash;
}
