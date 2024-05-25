/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.liaisondash;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 *
 * @author Joey
 */
public class PDFController {

    @FXML
    private VBox pdfPreviewContainer;
    
    private void importedData(){
        //import the data from the new excel row
    }
    
    public void setPDFPreview(VBox pdfPreview) {
        pdfPreviewContainer.getChildren().setAll(pdfPreview.getChildren());
    }
}
